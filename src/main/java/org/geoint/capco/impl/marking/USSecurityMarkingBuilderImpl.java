package org.geoint.capco.impl.marking;

import java.util.HashSet;
import java.util.Set;
import org.geoint.capco.SecurityPolicy;
import org.geoint.capco.impl.policy.SecurityPolicyImpl;
import org.geoint.capco.marking.AccmComponent;
import org.geoint.capco.marking.AeaComponent;
import org.geoint.capco.marking.ClassificationComponent;
import org.geoint.capco.marking.Country;
import org.geoint.capco.marking.DisseminationComponent;
import org.geoint.capco.marking.InvalidSecurityMarkingException;
import org.geoint.capco.marking.SapComponent;
import org.geoint.capco.marking.SciComponent;
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
                    + "valid classification component for this policy.");
            throw new InvalidSecurityMarkingException(m.toString(), sb.toString());
        }

        m.classification = policy.getClassificationPolicy().getComponent(classification);

        return this;
    }

    @Override
    public USSecurityMarkingBuilder addSCI(String... sci)
            throws InvalidSecurityMarkingException {

        for (String control : sci) {

            SciComponent component = policy.getSCIPolicy().getComponent(control);

            if (component == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Uknown SCI control '")
                        .append(control)
                        .append("' in security policy.");
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
            SapComponent sap = policy.getSAPPolicy().getComponent(sn);

            if (sap == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("'")
                        .append(sn)
                        .append("' is not "
                                + "a valid SAP program name, code word, or "
                                + "digraph/trigraph for this policy.");
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
                        .append("' in security policy.");
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
            AccmComponent ac = policy.getACCMPolicy().getComponent(control);
            if (ac == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown ACCM control '")
                        .append(control)
                        .append("' in security policy.");
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
    public USSecurityMarkingBuilder addFGICountry(String... countryCode)
            throws InvalidSecurityMarkingException {

    }

    @Override
    public USSecurityMarkingBuilder addRelCountry(String... countryCode)
            throws InvalidSecurityMarkingException {

    }

    @Override
    public USSecurityMarkingBuilder addDisplayCountry(String... countryCode)
            throws InvalidSecurityMarkingException {

    }

    @Override
    public String[] getAvailableClassifications() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAvailableSCI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAvailableSAP() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAvailableFGICountries() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAvailableAEA() {
        return new String[0];
    }

    @Override
    public String[] getAvailableRelCountries() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAvailableDisplayCountries() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAvailableDissemCountrols() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAvailableACCM() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        private Set<DisseminationComponent> diss = new HashSet<>();
        private Set<AccmComponent> accm = new HashSet<>();
        private final SecurityPolicy policy;

        public USSecurityMarkingImpl(SecurityPolicy policy) {
            this.policy = policy;
        }

        @Override
        public SciComponent[] getSCI() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public SapComponent[] getSAP() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isHVSACO() {
            return diss.contains(new DisseminationComponent(HVSACO_IDENTIFIER, HVSACO_IDENTIFIER));
        }

        @Override
        public AeaComponent getAEA() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Country[] getFGICountries() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Country[] getRelCountries() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Country[] getDisplayCountries() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public DisseminationComponent[] getDissemControls() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public AccmComponent[] getAccm() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String asPortion() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String asBanner() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public SecurityPolicy getPolicy() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public ClassificationComponent getClassification() {
            return classification;
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
