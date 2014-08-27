package org.geoint.capco.impl.marking;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.geoint.capco.impl.policy.SecurityPolicyImpl;
import org.geoint.capco.marking.component.AccmComponent;
import org.geoint.capco.marking.component.AeaComponent;
import org.geoint.capco.marking.component.ClassificationComponent;
import org.geoint.capco.marking.component.CountryComponent;
import org.geoint.capco.marking.component.DisplayToComponent;
import org.geoint.capco.marking.component.DisseminationComponent;
import org.geoint.capco.marking.component.FgiComponent;
import org.geoint.capco.marking.InvalidSecurityMarkingException;
import org.geoint.capco.marking.component.RelToComponent;
import org.geoint.capco.marking.component.SapComponent;
import org.geoint.capco.marking.component.SciComponent;
import org.geoint.capco.marking.SecurityMarking;
import org.geoint.capco.marking.USSecurityMarking;
import static org.geoint.capco.marking.USSecurityMarking.HVSACO_IDENTIFIER;
import org.geoint.capco.marking.USSecurityMarkingBuilder;

/**
 * Create an instance of a USSecurityMarking.
 *
 * This builder is not thread-safe.
 */
public class USSecurityMarkingBuilderImpl implements USSecurityMarkingBuilder {

    private final SecurityPolicyImpl policy;
    private final USSecurityMarkingImpl m;

    public USSecurityMarkingBuilderImpl(SecurityPolicyImpl policy) {
        this.policy = policy;
        this.m = new USSecurityMarkingImpl(policy);
    }

    @Override
    public USSecurityMarkingBuilder setClassification(final String classification)
            throws InvalidSecurityMarkingException {

        if (!policy.getClassificationPolicy().isComponentString(classification)) {
            StringBuilder sb = new StringBuilder();
            sb.append("'").append(classification).append("' is not a "
                    + "valid classification component for this policy '")
                    .append(policy.getName())
                    .append("'.");;
            throw new InvalidSecurityMarkingException(m.toString(), sb.toString());
        }

        m.classification = policy.getClassificationPolicy().getComponent(classification);

        return this;
    }

    @Override
    public USSecurityMarkingBuilder addSCI(String... sci)
            throws InvalidSecurityMarkingException {

        for (String control : sci) {

            SciComponent component = policy.getSciPolicy().getComponent(control);

            if (component == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Uknown SCI control '")
                        .append(control)
                        .append("' in security policy '")
                        .append(policy.getName())
                        .append("'.");
                throw new InvalidSecurityMarkingException(m.toString(), sb.toString());
            }

            if (!m.sci.contains(component)) {
                policy.isPermitted(m, component);
                m.sci.add(component);
            }
        }
        return this;
    }

    @Override
    public USSecurityMarkingBuilder addSAP(String... sapNames)
            throws InvalidSecurityMarkingException {
        //validate SAP component...we do this first (before checking if 
        //this should be "multiple" because we want to validate regardless

        boolean isMultiple = false;
        if (m.sap.size() == 1 && m.sap.iterator().next().isMultiple()) {
            isMultiple = true;
        }

        for (String sn : sapNames) {
            SapComponent sap = policy.getSapPolicy().getComponent(sn);

            if (sap == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("'")
                        .append(sn)
                        .append("' is not "
                                + "a valid SAP program name, code word, or "
                                + "digraph/trigraph for this policy '")
                        .append(policy.getName())
                        .append("'.");
                throw new InvalidSecurityMarkingException(m.toString(), sb.toString());
            }

            if (m.sap.contains(sap)) {
                policy.isPermitted(m, sap);

                if (isMultiple) {
                    //disregard
                    continue;
                }

                m.sap.add(sap);
            }

            //check if we're 4 or over now...we're using a Set, so it'll
            //only store a SapComponent once
            if (m.sap.size() >= 4) {
                isMultiple = true;
                m.sap.clear();
                m.sap.add(SapComponent.MULTIPLE);
            }
        }
        return this;
    }

    @Override
    public USSecurityMarkingBuilder addDissemControl(String... controls)
            throws InvalidSecurityMarkingException {
        for (String control : controls) {
            DisseminationComponent diss = policy.getDisseminationPolicy().getComponent(control);

            if (diss == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown dissemination control '")
                        .append(control)
                        .append("' in security policy '")
                        .append(policy.getName())
                        .append("'.");
                throw new InvalidSecurityMarkingException(m.toString(), sb.toString());
            }

            if (!m.diss.contains(diss)) {
                policy.isPermitted(m, diss);
                m.diss.add(diss);
            }
        }
        return this;
    }

    @Override
    public USSecurityMarkingBuilder setSpecialAccessChannelsOnly(boolean b)
            throws InvalidSecurityMarkingException {
        addDissemControl(HVSACO_IDENTIFIER);
        return this;
    }

    @Override
    public USSecurityMarkingBuilder setAEA(String aea)
            throws InvalidSecurityMarkingException {
        //TODO add AEA support
        throw new UnsupportedOperationException("Currently not supported.");
    }

    @Override
    public USSecurityMarkingBuilder addACCM(String... accm)
            throws InvalidSecurityMarkingException {
        for (String control : accm) {
            AccmComponent ac = policy.getAccmPolicy().getComponent(control);
            if (ac == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown ACCM control '")
                        .append(control)
                        .append("' in security policy '")
                        .append(policy.getName())
                        .append("'.");
                throw new InvalidSecurityMarkingException(m.toString(), sb.toString());
            }

            if (!m.accm.contains(ac)) {
                policy.isPermitted(m, ac);
                m.accm.add(ac);
            }
        }
        return this;
    }

    @Override
    public USSecurityMarkingBuilder addFGICountry(String... countryCodes)
            throws InvalidSecurityMarkingException {
        for (String code : countryCodes) {
            FgiComponent f = policy.getFgiPolicy().getComponent(code);

            if (f == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown FGI country '")
                        .append(code)
                        .append("' in security policy '")
                        .append(policy.getName())
                        .append("'.");
                throw new InvalidSecurityMarkingException(m.toString(), sb.toString());
            }

            if (!m.fgi.contains(f)) {
                policy.isPermitted(m, f);
                m.fgi.add(f);
            }
        }
        return this;
    }

    @Override
    public USSecurityMarkingBuilder addRelCountry(String... countryCodes)
            throws InvalidSecurityMarkingException {
        for (String code : countryCodes) {
            RelToComponent r = policy.getRelPolicy().getComponent(code);

            if (r == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown REL country '")
                        .append(code)
                        .append("' in security policy '")
                        .append(policy.getName())
                        .append("'.");
                throw new InvalidSecurityMarkingException(m.toString(), sb.toString());
            }

            if (!m.rel.contains(r)) {
                policy.isPermitted(m, r);
                m.rel.add(r);
            }
        }
        return this;
    }

    @Override
    public USSecurityMarkingBuilder addDisplayCountry(String... countryCodes)
            throws InvalidSecurityMarkingException {
        for (String code : countryCodes) {
            DisplayToComponent d = policy.getDisplayPolicy().getComponent(code);

            if (d == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown Display country '")
                        .append(code)
                        .append("' in security policy '")
                        .append(policy.getName())
                        .append("'.");
                throw new InvalidSecurityMarkingException(m.toString(), sb.toString());
            }

            if (!m.disp.contains(d)) {
                policy.isPermitted(m, d);
                m.disp.add(d);
            }
        }
        return this;
    }

    @Override
    public AeaComponent[] getAvailableAEA() {
        return new AeaComponent[0];
    }

    @Override
    public ClassificationComponent[] getAvailableClassifications() {
        return policy.getClassificationPolicy().getAvailable(m);
    }

    @Override
    public SciComponent[] getAvailableSCI() {
        return policy.getSciPolicy().getAvailable(m);
    }

    @Override
    public SapComponent[] getAvailableSAP() {
        return policy.getSapPolicy().getAvailable(m);
    }

    @Override
    public FgiComponent[] getAvailableFGICountries() {
        return policy.getFgiPolicy().getAvailable(m);
    }

    @Override
    public RelToComponent[] getAvailableRelCountries() {
        return policy.getRelPolicy().getAvailable(m);
    }

    @Override
    public DisplayToComponent[] getAvailableDisplayCountries() {
        return policy.getDisplayPolicy().getAvailable(m);
    }

    @Override
    public DisseminationComponent[] getAvailableDissemCountrols() {
        return policy.getDisseminationPolicy().getAvailable(m);
    }

    @Override
    public AccmComponent[] getAvailableACCM() {
        return policy.getAccmPolicy().getAvailable(m);
    }

    @Override
    public USSecurityMarking build() throws InvalidSecurityMarkingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class USSecurityMarkingImpl
            implements USSecurityMarking {

        private ClassificationComponent classification;
        private Set<SapComponent> sap = new HashSet<>(); //SecurityMarkingBuilderImpl relies on this being a Set, if this is changed, update this
        private Set<SciComponent> sci = new HashSet<>();
        private Set<FgiComponent> fgi = new HashSet<>();
        private Set<DisseminationComponent> diss = new HashSet<>();
        private Set<RelToComponent> rel = new HashSet<>();
        private Set<DisplayToComponent> disp = new HashSet<>();
        private Set<AccmComponent> accm = new HashSet<>();
        private final SecurityPolicyImpl policy;

        public USSecurityMarkingImpl(SecurityPolicyImpl policy) {
            this.policy = policy;
        }

        @Override
        public SciComponent[] getSCI() {
            return (SciComponent[]) sci.toArray();
        }

        @Override
        public SapComponent[] getSAP() {
            return (SapComponent[]) sap.toArray();
        }

        @Override
        public boolean isHVSACO() {
            return diss.contains(new DisseminationComponent(HVSACO_IDENTIFIER, HVSACO_IDENTIFIER));
        }

        @Override
        public AeaComponent getAEA() {
            return null;
        }

        @Override
        public CountryComponent[] getFGICountries() {
            return (CountryComponent[]) fgi.toArray();
        }

        @Override
        public CountryComponent[] getRelCountries() {
            return (CountryComponent[]) rel.toArray();
        }

        @Override
        public CountryComponent[] getDisplayCountries() {
            return (CountryComponent[]) disp.toArray();
        }

        @Override
        public DisseminationComponent[] getDissemControls() {
            return (DisseminationComponent[]) diss.toArray();
        }

        @Override
        public AccmComponent[] getAccm() {
            return (AccmComponent[]) accm.toArray();
        }

        @Override
        public SecurityPolicyImpl getPolicy() {
            return policy;
        }

        @Override
        public ClassificationComponent getClassification() {
            return classification;
        }

        @Override
        public String asPortion() {
            return stringify(true);
        }

        @Override
        public String asBanner() {
            return stringify(false);
        }

        private String stringify(boolean portion) {
            StringBuilder sb = new StringBuilder();
            sb.append((portion) ? classification.getPortion() : classification.getBanner())
                    .append(SecurityMarking.COMPONENT_SEPARATOR);
            if (!sci.isEmpty()) {
                appendSci(sb, portion);
            }
        }

        private void appendSci(StringBuilder sb, boolean portion) {
            Iterator<SciComponent> iterator = sci.iterator();
            while (iterator.hasNext()) {
                SciComponent c = iterator.next();
                if (portion) {
                    sb.append(c.getPortion());
                } else {
                    sb.append(c.getBanner());
                }
                if (iterator.hasNext()) {
                    sb.append(SecurityMarking.SUBCOMPONENT_SLASH_SEPARATOR);
                }
            }
        }

        @Override
        public SecurityMarking merge(SecurityMarking marking) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isPermitted(SecurityMarking marking) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public byte[] asBytes() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
}
