package org.geoint.capco.impl.policy;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.geoint.capco.marking.ForeignSecurityMarking;
import org.geoint.capco.marking.ForeignSecurityMarkingBuilder;
import org.geoint.capco.marking.InvalidSecurityMarkingException;
import org.geoint.capco.marking.JointSecurityMarking;
import org.geoint.capco.marking.JointSecurityMarkingBuilder;
import org.geoint.capco.SecurityPolicy;
import org.geoint.capco.marking.USSecurityMarking;
import org.geoint.capco.marking.USSecurityMarkingBuilder;
import org.geoint.capco.impl.marking.USSecurityMarkingBuilderImpl;
import org.geoint.capco.impl.marking.USSecurityMarkingParserImpl;
import org.geoint.capco.marking.AccmComponent;
import org.geoint.capco.marking.AeaComponent;
import org.geoint.capco.marking.ClassificationComponent;
import org.geoint.capco.marking.Country;
import org.geoint.capco.marking.DisseminationComponent;
import org.geoint.capco.marking.SapComponent;
import org.geoint.capco.marking.SciComponent;
import org.geoint.capco.marking.SecurityMarking;

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
        return new USSecurityMarkingBuilderImpl();
    }

    @Override
    public JointSecurityMarkingBuilder jointBuilder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ForeignSecurityMarkingBuilder foreignBuilder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SecurityMarking valueOf(byte[] marking) throws InvalidSecurityMarkingException {
        return valueOf(null, marking);
    }

    @Override
    public SecurityMarking valueOf(SecurityMarking context, byte[] marking)
            throws InvalidSecurityMarkingException {
        return valueOf(context, new String(marking, MARKING_CHARSET));
    }

    @Override
    public SecurityMarking valueOf(String marking)
            throws InvalidSecurityMarkingException {
        return valueOf(null, marking);
    }

    @Override
    public SecurityMarking valueOf(SecurityMarking context, String marking)
            throws InvalidSecurityMarkingException {
        if (marking.startsWith(SecurityMarking.COMPONENT_SEPARATOR)) {
            if (marking.startsWith(
                    SecurityMarking.COMPONENT_SEPARATOR
                    + JointSecurityMarking.HEADER
            )) {
                return valueOfJoint(context, marking);
            } else {
                return valueOfForeign(context, marking);
            }
        }
        return valueOfUS(context, marking);
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
    private JointSecurityMarking valueOfJoint(SecurityMarking context, String marking)
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
    private ForeignSecurityMarking valueOfForeign(SecurityMarking context, String marking)
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
    private USSecurityMarking valueOfUS(SecurityMarking context, String marking)
            throws InvalidSecurityMarkingException {
        USSecurityMarkingParserImpl parser = new USSecurityMarkingParserImpl(this);
        return parser.parse(context, marking);
    }

    @Override
    public boolean isPermitted(SecurityMarking m1, SecurityMarking m2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SecurityMarking merge(SecurityMarking... markings) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public String[] getAllClassifications() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public String[] getAllSCI() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public String[] getAllSAP() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public String[] getAllFGICountries() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public String[] getAllAEA() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public String[] getAllRelCountries() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public String[] getAllDisplayCountries() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public String[] getAllDissemControls() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public String[] getAllACCM() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

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
}
