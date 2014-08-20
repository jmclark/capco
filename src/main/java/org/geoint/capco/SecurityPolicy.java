package org.geoint.capco;

import org.geoint.capco.impl.policy.ComponentRestrictionException;
import org.geoint.capco.marking.USSecurityMarkingBuilder;
import org.geoint.capco.marking.InvalidSecurityMarkingException;
import org.geoint.capco.marking.JointSecurityMarkingBuilder;
import org.geoint.capco.marking.ForeignSecurityMarkingBuilder;
import org.geoint.capco.marking.MarkingComponent;
import org.geoint.capco.marking.SecurityMarking;
import org.geoint.capco.marking.USSecurityMarking;

/**
 * A security policy is configured to manage the available security marking
 * options and restrictions, allowing for applications to be configured for
 * their operating environment. Separating policies from logic also allows us to
 * manage multiple policies simultaneously in a single application.
 * <p>
 * Multiple policies are useful when, for example, data must be moved across
 * security domains with different security policies. Such an application can
 * maintain both policies and ensure that the data transferring to the other
 * system can support the data before sending the data over to the other domain
 * for validation.
 *
 */
public interface SecurityPolicy {

    /**
     * Return the (JVM) unique name of the security policy
     *
     * @return
     */
    String getName();

    /**
     * Return a {@link USSecurityMarking} builder for programmatic construction
     * of a US security marking.
     *
     * The builder is NOT thread-safe.
     *
     * @return
     */
    USSecurityMarkingBuilder builder();

    /**
     * Returns a builder for programmatic construction of a joint security
     * marking.
     *
     * @return
     */
    JointSecurityMarkingBuilder jointBuilder();

    /**
     * Returns a builder for programmatic construction of a foreign security
     * marking.
     *
     * @return
     */
    ForeignSecurityMarkingBuilder foreignBuilder();

    /**
     * Parses the provided String and returns the relevant
     * {@link SecurityMarking} for this policy
     *
     * @param marking stringified version of a SecurityMarking
     * @return
     * @throws InvalidSecurityMarkingException if String is not a valid security
     * marking for this policy
     */
    SecurityMarking valueOf(String marking) throws InvalidSecurityMarkingException;

    /**
     * Parses the provided String within the context of the provided overall
     * (banner) security marking.
     *
     * @param context
     * @param marking
     * @return
     * @throws InvalidSecurityMarkingException
     */
    SecurityMarking valueOf(SecurityMarking context, String marking)
            throws InvalidSecurityMarkingException;

    /**
     * Parse the provided byte array and return the relevant
     * {@link SecurityMarking} for this policy.
     *
     * @param marking
     * @return
     * @throws InvalidSecurityMarkingException is byte array is not a valid
     * security marking for this policy
     */
    SecurityMarking valueOf(byte[] marking) throws InvalidSecurityMarkingException;

    /**
     * Parses the provided byte array within the context of the provided overall
     * (banner) security marking.
     *
     * @param context
     * @param marking
     * @return
     * @throws InvalidSecurityMarkingException
     */
    SecurityMarking valueOf(SecurityMarking context, byte[] marking)
            throws InvalidSecurityMarkingException;

    /**
     * Compares the security markings to determine if the second parameter is
     * permitted within the contenxt of the first.
     *
     * @param m1 base marking
     * @param m2 comparison marking
     * @return
     * @throws InvalidSecurityMarkingException if markings are not from the same policy and could
     * not be converted
     */
    boolean isPermitted(SecurityMarking m1, SecurityMarking m2) 
            throws InvalidSecurityMarkingException;

    /**
     * Determines if the component can be added to the marking within the scope
     * of this policy.
     *
     * @param marking
     * @param component
     * @return
     * @throws ComponentRestrictionException
     * @throws InvalidSecurityMarkingException
     */
    boolean isPermitted(SecurityMarking marking, MarkingComponent component)
            throws ComponentRestrictionException, InvalidSecurityMarkingException;

    /**
     * Merges the provided markings into a single, encompassing, marking
     *
     * @param markings
     * @return
     * @throws CapcoException if markings are not from the same policy and could
     * not be converted
     */
    SecurityMarking merge(SecurityMarking... markings) throws CapcoException;

}
