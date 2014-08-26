package org.geoint.capco.impl.policy;

import java.nio.charset.Charset;
import org.geoint.capco.CapcoException;
import org.geoint.capco.SecurityPolicy;
import org.geoint.capco.marking.ForeignSecurityMarking;
import org.geoint.capco.marking.ForeignSecurityMarkingBuilder;
import org.geoint.capco.marking.InvalidSecurityMarkingException;
import org.geoint.capco.marking.JointSecurityMarking;
import org.geoint.capco.marking.JointSecurityMarkingBuilder;
import org.geoint.capco.marking.USSecurityMarking;
import org.geoint.capco.marking.USSecurityMarkingBuilder;
import org.geoint.capco.impl.marking.USSecurityMarkingBuilderImpl;
import org.geoint.capco.impl.marking.USSecurityMarkingParserImpl;
import org.geoint.capco.marking.AccmComponent;
import org.geoint.capco.marking.AeaComponent;
import org.geoint.capco.marking.ClassificationComponent;
import org.geoint.capco.marking.DisplayToComponent;
import org.geoint.capco.marking.DisseminationComponent;
import org.geoint.capco.marking.FgiComponent;
import org.geoint.capco.marking.MarkingComponent;
import org.geoint.capco.marking.RelToComponent;
import org.geoint.capco.marking.SciComponent;
import org.geoint.capco.marking.SecurityMarking;
import org.geoint.capco.spi.store.SecurityPolicyStore;

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
    private final SecurityPolicyStore store;

    private static final Charset MARKING_CHARSET = Charset.forName("UTF-8");

    public SecurityPolicyImpl(String name, SecurityPolicyStore store) {
        this.name = name;
        this.store = store;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public USSecurityMarkingBuilder builder() {
        return new USSecurityMarkingBuilderImpl(this);
    }

    @Override
    public JointSecurityMarkingBuilder jointBuilder() {
        throw new UnsupportedOperationException("Joint markings are not yet supported.");
    }

    @Override
    public ForeignSecurityMarkingBuilder foreignBuilder() {
        throw new UnsupportedOperationException("Foreign markings are not yet supported.");
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

    @Override
    public boolean isPermitted(final SecurityMarking m1, final SecurityMarking m2)
            throws InvalidSecurityMarkingException {
        return localize(m1).isPermitted(localize(m2));
    }

    @Override
    public boolean isPermitted(SecurityMarking marking, MarkingComponent component)
            throws ComponentRestrictionException, InvalidSecurityMarkingException {
        //TODO this is done synchornously, but may be worth trying a concurrent 
        //approach when the number of restrictions get high (fork/join...
        //perhaps with some kind of interrupt?)
        synchronousRestrictionCheck(marking, component);
        return true;
    }

    private void synchronousRestrictionCheck(SecurityMarking marking,
            MarkingComponent component)
            throws ComponentRestrictionException, InvalidSecurityMarkingException {
        SecurityMarking lm = localize(marking);
        for (ComponentRestriction r : store.getRestrictions()) {
            if (!r.isPermitted(lm, component)) {
                StringBuilder sb = new StringBuilder();
                sb.append("'")
                        .append(marking.toString())
                        .append("' cannot include ")
                        .append(component.getClass().getSimpleName())
                        .append(" component '")
                        .append(component.toString())
                        .append("' in policy '")
                        .append(this.name)
                        .append("'.");
                throw new ComponentRestrictionException(
                        component, r, marking.toString(), sb.toString());
            }
        }
    }

    @Override
    public SecurityMarking merge(SecurityMarking... markings)
            throws CapcoException {

        SecurityMarking result = null;
        for (SecurityMarking m : markings) {
            result = localize(m).merge(result);
        }
        return result;
    }

    public ComponentPolicy<ClassificationComponent> getClassificationPolicy() {
        return store.getClassificationPolicy();
    }

    public ComponentPolicy<SciComponent> getSCIPolicy() {
        return store.getSCIPolicy();
    }

    public SapComponentPolicy getSAPPolicy() {
        return store.getSAPPolicy();
    }

    public ComponentPolicy<FgiComponent> getFGIPolicy() {
        return store.getFGIPolicy();
    }

    public ComponentPolicy<AeaComponent> getAEAPolicy() {
        return store.getAEAPolicy();
    }

    public ComponentPolicy<RelToComponent> getRelPolicy() {
        return store.getRelPolicy();
    }

    public ComponentPolicy<DisplayToComponent> getDisplayPolicy() {
        return store.getDisplayPolicy();
    }

    public ComponentPolicy<DisseminationComponent> getDisseminationPolicy() {
        return store.getDisseminationPolicy();
    }

    public ComponentPolicy<AccmComponent> getACCMPolicy() {
        return store.getACCMPolicy();
    }

    /**
     * Converts the provided SecurityMarking to this policy, as needed.
     *
     * @param marking
     * @return
     * @throws CapcoException
     */
    private SecurityMarking localize(SecurityMarking marking)
            throws InvalidSecurityMarkingException {
        return (!marking.getPolicy().equals(this))
                ? this.valueOf(marking.toString())
                : marking;
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

}
