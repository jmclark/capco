package org.geoint.capco.impl.policy;

import org.geoint.capco.marking.Country;
import org.geoint.capco.marking.DisseminationComponent;
import org.geoint.capco.marking.DisplayToComponent;
import org.geoint.capco.marking.SciComponent;
import org.geoint.capco.marking.AeaComponent;
import org.geoint.capco.marking.AccmComponent;
import org.geoint.capco.marking.SapComponent;
import org.geoint.capco.marking.ClassificationComponent;
import org.geoint.capco.marking.RelToComponent;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.geoint.capco.ForeignSecurityMarking;
import org.geoint.capco.ForeignSecurityMarkingBuilder;
import org.geoint.capco.InvalidSecurityMarkingException;
import org.geoint.capco.JointSecurityMarking;
import org.geoint.capco.JointSecurityMarkingBuilder;
import org.geoint.capco.marking.SecurityMarking;
import org.geoint.capco.SecurityPolicy;
import org.geoint.capco.USSecurityMarking;
import org.geoint.capco.USSecurityMarkingBuilder;

/**
 * This policy implementation allows for structural knowledge of the CAPCO
 * specification, but does not contain logic for CAPCO controls (that are not
 * structural). Specific controls must be defined in the security policy
 * configuration.
 *
 * This class is thread-safe.
 */
public class SecurityPolicyImpl implements SecurityPolicy {

    private final String name;

    //indicies
    private final Map<String, Country> countries = new HashMap<>(); //key is trigraph
    private static final Charset MARKING_CHARSET = Charset.forName("UTF-8");


    public SecurityPolicyImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public USSecurityMarkingBuilder builder() {
        return new USSecurityMarkingBuilder();
    }

    @Override
    public JointSecurityMarkingBuilder jointBuilder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ForeignSecurityMarkingBuilder foreignBuilder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    /**
     * Expects the byte[] is a UTF-8 string
     *
     * @param marking
     * @return
     * @throws InvalidSecurityMarkingException
     */
    @Override
    public SecurityMarking valueOf(byte[] marking) throws InvalidSecurityMarkingException {
        return valueOf(new String(marking, MARKING_CHARSET));
    }

    /**
     * Parses the CAPCO classification marking.
     *
     * @param marking
     * @return
     * @throws InvalidSecurityMarkingException
     */
    @Override
    public SecurityMarking valueOf(String marking)
            throws InvalidSecurityMarkingException {
        if (marking.startsWith(AbstractSecurityMarkingImpl.COMPONENT_SEPARATOR)) {
            if (marking.startsWith(
                    AbstractSecurityMarkingImpl.COMPONENT_SEPARATOR
                    + JointSecurityMarkingImpl.HEADER
            )) {
                return valueOfJoint(marking);
            } else {
                return valueOfNonUS(marking);
            }
        }
        return valueOfUS(marking);
    }

    /**
     * Parses CAPCO classification markings expected in the form:
     *
     * //JOINT [classification] [country codes]
     *
     * @param marking
     * @return
     * @throws InvalidSecurityMarkingException
     */
    private SecurityMarking valueOfJoint(String marking)
            throws InvalidSecurityMarkingException {
        throw new InvalidSecurityMarkingException(marking, "Joing markings not yet supported by policy.");
    }

    /**
     * Parses CAPCO classification markings expected in the form:
     *
     * //[country code] [non-U.S. classification]
     *
     * @param marking
     * @return
     * @throws InvalidSecurityMarkingException
     */
    private SecurityMarking valueOfNonUS(String marking)
            throws InvalidSecurityMarkingException {
        throw new InvalidSecurityMarkingException(marking, "NON-US markings not yet supported by policy.");
    }

    /**
     * Parses CAPCO classification marking expected in the form:
     *
     * CLASSIFICATION//SCI/SCI-SUBCONTROL//SAP//AEA//FGI//DISSEM/DISSEM//OTHER
     * DISSEM
     *
     * @param marking
     * @return
     * @throws InvalidSecurityMarkingException
     */
    private SecurityMarking valueOfUS(String marking)
            throws InvalidSecurityMarkingException {
        return new USMarkingParser().parse(marking);
    }

    @Override
    public boolean isPermitted(SecurityMarking m1, SecurityMarking m2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SecurityMarking merge(SecurityMarking... markings) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAllClassifications() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAllSCI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAllSAP() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAllFGICountries() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAllAEA() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAllRelCountries() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAllDisplayCountries() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAllDissemControls() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAllACCM() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * parse/validate a US security marking.
     *
     * CLASSIFICATION//SCI/SCI-SUBCONTROL//SAP//AEA//FGI//DISSEM/DISSEM//OTHER
     * DISSEM
     * <p>
     * This parser is thread-safe.
     */
    private class USMarkingParser {

        private SecurityMarking context;

        public USMarkingParser() {
        }

        /**
         * @param context the context of a security marking (ie parsing a
         * portion mark within a page marking)
         */
        public USMarkingParser(SecurityMarking context) {
            this.context = context;
        }

        public SecurityMarking parse(String marking)
                throws InvalidSecurityMarkingException {
            
        }


    private class USSecurityMarkingBuilderImpl implements USSecurityMarkingBuilder {

        AbstractSecurityMarkingImpl m;

        private USSecurityMarkingImpl getUSMarking()
                throws InvalidSecurityMarkingException {
            if (m == null) {
                m = new USSecurityMarkingImpl();
            } else if (!(m instanceof USSecurityMarkingImpl)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Cannot add US security marking component, builder "
                        + "is creating a ")
                        .append(m.getClass().getName())
                        .append(" marking");
                throw new InvalidSecurityMarkingException(m.toString(), sb.toString());
            }
            return (USSecurityMarkingImpl) m;
        }

        @Override
        public USSecurityMarkingBuilder setClassification(final String classification)
                throws InvalidSecurityMarkingException {
            readLock.lock();
            try {
                if (classificationPortionMarks.containsKey(classification)) {
                    m.classification = classificationPortionMarks.get(classification);
                } else if (classificationBannerMarks.containsKey(classification)) {
                    m.classification = classificationBannerMarks.get(classification);
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("'").append(classification).append("' is not a "
                            + "valid classification component for this policy.");
                    throw new InvalidSecurityMarkingException(m.toString(), sb.toString());
                }
            } finally {
                readLock.unlock();
            }
            return this;
        }

        @Override
        public USSecurityMarkingBuilder addSCI(String... sci)
                throws InvalidSecurityMarkingException {
            USSecurityMarkingImpl usm = getUSMarking();
            readLock.lock();
            try {
                for (String control : sci) {
                    if (sciBannerMarks.containsKey(control)) {
                        usm.sci.add(sciBannerMarks.get(control));
                    } else if (sciPortionMarks.containsKey(control)) {
                        usm.sci.add(sciPortionMarks.get(control));
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Uknown SCI control '")
                                .append(control)
                                .append("' in security policy.");
                        throw new InvalidSecurityMarkingException(m.toString(), sb.toString());
                    }
                }
            } finally {
                readLock.unlock();
            }
            return this;
        }

        @Override
        public USSecurityMarkingBuilder addSAP(String... sapNames)
                throws InvalidSecurityMarkingException {
            //validate SAP component...we do this first (before checking if 
            //this should be "multiple" because we want to validate regardless

            USSecurityMarkingImpl usm = getUSMarking();
            readLock.lock();
            try {
                boolean isMultiple = false;
                if (usm.sap.size() == 1 && usm.sap.iterator().next().isMultiple()) {
                    isMultiple = true;
                }

                for (String sn : sapNames) {
                    SapComponent sap = null;

                    if (sapProgramDigraphMarks.containsKey(sn)) {
                        sap = (sapProgramDigraphMarks.get(sn));
                    } else if (sapCodeWords.containsKey(sn)) {
                        sap = (sapCodeWords.get(sn));
                    } else if (sapProgramNames.containsKey(sn)) {
                        sap = (sapProgramNames.get(sn));
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("'")
                                .append(sn)
                                .append("' is not "
                                        + "a valid SAP program name, code word, or "
                                        + "digraph/trigraph for this policy.");
                        throw new InvalidSecurityMarkingException(m.toString(), sb.toString());
                    }

                    if (isMultiple) {
                        //disregard
                        continue;
                    }

                    usm.sap.add(sap);

                    //check if we're 4 or over now...we're using a Set, so it'll
                    //only store a SapComponent once
                    if (usm.sap.size() >= 4) {
                        isMultiple = true;
                        usm.sap.clear();
                        usm.sap.add(SapComponent.MULTIPLE);
                    }
                }
            } finally {
                readLock.unlock();
            }
            return this;
        }

        @Override
        public USSecurityMarkingBuilder setSpecialAccessChannelsOnly(boolean b) throws InvalidSecurityMarkingException {
            addDissemControl(HVSACO_IDENTIFIER);
            return this;
        }

        @Override
        public USSecurityMarkingBuilder setAEA(String aea) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public USSecurityMarkingBuilder addFGICountry(String... countryCode) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public USSecurityMarkingBuilder addRelCountry(String... countryCode) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public USSecurityMarkingBuilder addDisplayCountry(String... countryCode) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public USSecurityMarkingBuilder addDissemControl(String... controls)
                throws InvalidSecurityMarkingException {
            USSecurityMarkingImpl usm = getUSMarking();
            readLock.lock();
            try {
                for (String control : controls) {
                    if (disPortionMarks.containsKey(control)) {
                        usm.diss.add(disPortionMarks.get(control));
                    } else if (disBannerMarks.containsKey(control)) {
                        usm.diss.add(disBannerMarks.get(control));
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Unknown dissemination control '")
                                .append(control)
                                .append("' in security policy.");
                        throw new InvalidSecurityMarkingException(m.toString(), sb.toString());
                    }
                }
            } finally {
                readLock.unlock();
            }
            return this;
        }

        @Override
        public USSecurityMarkingBuilder addACCM(String... accm) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    }

    private abstract class AbstractSecurityMarkingImpl implements SecurityMarking {

        public static final String COMPONENT_SEPARATOR = "//";
        ClassificationComponent classification;

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

    private class USSecurityMarkingImpl extends AbstractSecurityMarkingImpl
            implements USSecurityMarking {

        Set<SapComponent> sap = new HashSet<>(); //SecurityMarkingBuilderImpl relies on this being a Set, if this is changed, update this
        Set<SciComponent> sci = new HashSet<>();
        Set<DisseminationComponent> diss = new HashSet<>();

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

    }

    private class JointSecurityMarkingImpl extends USSecurityMarkingImpl
            implements JointSecurityMarking {

        public static final String HEADER = "//JOINT";

        @Override
        public Country[] getContributingCountries() {
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
    }

    private class ForeignSecurityMarkingImpl extends AbstractSecurityMarkingImpl
            implements ForeignSecurityMarking {

        @Override
        public String asPortion() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String asBanner() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Country getOriginatingCountry() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
}
