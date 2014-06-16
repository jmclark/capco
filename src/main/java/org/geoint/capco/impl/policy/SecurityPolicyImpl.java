package org.geoint.capco.impl.policy;

import java.nio.charset.Charset;
import org.geoint.capco.ForeignSecurityMarking;
import org.geoint.capco.InvalidSecurityMarkingException;
import org.geoint.capco.JointSecurityMarking;
import org.geoint.capco.SecurityMarking;
import org.geoint.capco.SecurityMarkingBuilder;
import org.geoint.capco.SecurityPolicy;
import org.geoint.capco.USSecurityMarking;

/**
 *
 */
public class SecurityPolicyImpl implements SecurityPolicy {

    private final String name;
    private static final Charset MARKING_CHARSET = Charset.forName("UTF-8");
    

    public SecurityPolicyImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public SecurityMarkingBuilder builder() {
        return new SecurityMarkingBuilderImpl();
    }

    /**
     * Finite-state machine used to parse/validate a US security marking
     *
     * CLASSIFICATION//SCI/SCI-SUBCONTROL//SAP//AEA//FGI//DISSEM/DISSEM//OTHER
     * DISSEM
     *
     */
    private class USMarkingParser {

        private USComponent component;

        public SecurityMarking parse(String marking)
                throws InvalidSecurityMarkingException {
            if (marking == null) {
                throw new InvalidSecurityMarkingException("Marking cannot be null.");
            }

            String[] components = marking.split("//");

            if (components.length == 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("'")
                        .append(marking)
                        .append("' is not a valid security marking within the "
                                + "Security Policy '")
                        .append(getName())
                        .append("'.");
                throw new InvalidSecurityMarkingException(sb.toString());
            }
            
            //component 0 should be the classification component
            final String classification = components[0];
            
        }
    }

    private enum USComponent {

        //ordinal sequence is significant
        classification, sci, sap, awa, fgi, dissem, other;
    }

    /**
     * Parses the CAPCO classification marking.
     *
     * @param marking
     * @return
     * @throws InvalidSecurityMarkingException
     */
    @Override
    public SecurityMarking valueOf(String marking) throws InvalidSecurityMarkingException {
        if (marking.startsWith(AbstractSecurityMarkingImpl.COMPONENT_SEPARATOR)) {
            if (marking.startsWith(JointSecurityMarkingImpl.HEADER)) {
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
    private SecurityMarking valueOfJoint(String marking) throws InvalidSecurityMarkingException {

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
    private SecurityMarking valueOfNonUS(String marking) throws InvalidSecurityMarkingException {

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
    private SecurityMarking valueOfUS(String marking) throws InvalidSecurityMarkingException {

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

    private class SecurityMarkingBuilderImpl implements SecurityMarkingBuilder {

        @Override
        public SecurityMarkingBuilder setOwningCountry(String countryCode) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public SecurityMarkingBuilder addContributingCountry(String... countryCode) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public SecurityMarkingBuilder setClassification(String classificiation) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public SecurityMarkingBuilder addSCI(String... sci) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public SecurityMarkingBuilder addSAP(String... sap) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public SecurityMarkingBuilder setAEA(String aea) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public SecurityMarkingBuilder addFGICountry(String... countryCode) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public SecurityMarkingBuilder addRelCountry(String... countryCode) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public SecurityMarkingBuilder addDisplayCountry(String... countryCode) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public SecurityMarkingBuilder addDissemControl(String... countryCode) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public SecurityMarkingBuilder addACCM(String... accm) {
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

    }

    private abstract class AbstractSecurityMarkingImpl implements SecurityMarking {

        public static final String COMPONENT_SEPARATOR = "//";

        @Override
        public SecurityPolicy getPolicy() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getClassification() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        public String asString() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public byte[] asBytes() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    private class USSecurityMarkingImpl extends AbstractSecurityMarkingImpl
            implements USSecurityMarking {

        @Override
        public String[] getSCI() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String[] getSAP() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getAEA() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String[] getFGICountries() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String[] getRelCountries() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String[] getDisplayCountries() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String[] getDissemControls() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String[] getAccm() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    private class JointSecurityMarkingImpl extends USSecurityMarkingImpl
            implements JointSecurityMarking {

        public static final String HEADER = "//JOINT";

        @Override
        public String[] getContributingCountries() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    private class ForeignSecurityMarkingImpl extends AbstractSecurityMarkingImpl
            implements ForeignSecurityMarking {

        @Override
        public String getOriginatingCountry() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
}
