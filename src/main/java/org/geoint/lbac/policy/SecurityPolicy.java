package org.geoint.lbac.policy;

import org.geoint.lbac.LbacException;
import org.geoint.lbac.marking.InvalidSecurityMarkingException;
import org.geoint.lbac.marking.SecurityMarking;
import org.geoint.lbac.marking.SecurityMarkingBuilder;

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
     * permitted within the context of the first.
     *
     * @param m1 base marking
     * @param m2 comparison marking
     * @return
     * @throws InvalidSecurityMarkingException if markings are not from the same
     * policy and could not be converted
     */
    boolean isPermitted(SecurityMarking m1, SecurityMarking m2)
            throws InvalidSecurityMarkingException;

    /**
     * Merges the provided markings into a single, encompassing, marking
     *
     * @param markings
     * @return
     * @throws LbacException if markings are not from the same policy and could
     * not be converted
     */
    SecurityMarking merge(SecurityMarking... markings) throws LbacException;

    /**
     * Converts the provided markings and merges them into an overall
     * classification.
     *
     * @param markings
     * @return
     * @throws LbacException
     */
    SecurityMarking merge(String... markings) throws LbacException;

    /**
     * Returns the category names for this policy.
     *
     * @return
     */
    String[] getCategoryNames();

    /**
     * Returns the category policy for the specified category.
     *
     * @param categoryName
     * @return
     */
    CategoryPolicy getCategory(String categoryName);

    /**
     * Returns a marking builder for this policy.
     *
     * @return
     */
    SecurityMarkingBuilder markingBuilder();

}
