package org.geoint.capco;

/**
 * A security policy is configured to manage the available security marking
 * options and restrictions, allowing for applications to manage multiple
 * policies.
 * <p>
 * Multiple policies is helpful when, for example, data must be moved across
 * security domains with different security policies.
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
     * Return a {@link SecurityMarking} builder for programmatic construction of
     * a marking.
     *
     * @return
     */
    SecurityMarkingBuilder builder();

    /**
     * Parses the provided String and returns the relevant
     * {@link SecurityMarking} for this policy
     *
     * @param marking stringified version of a SecurityMarking
     * @return
     * @throws org.geoint.capco.InvalidSecurityMarkingException if String is not
     * a valid security marking for this policy
     */
    SecurityMarking valueOf(String marking) throws InvalidSecurityMarkingException;

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
     * Compares the security markings to determine if the second parameter is
     * permitted within the contenxt of the first.
     *
     * @param m1 base marking
     * @param m2 comparison marking
     * @return
     */
    boolean isPermitted(SecurityMarking m1, SecurityMarking m2);

    /**
     * Merges the provided markings into a single, encompassing, marking
     *
     * @param markings
     * @return
     */
    SecurityMarking merge(SecurityMarking... markings);

    /**
     * Return all the potential classification values based on the policy and
     * the requesting users permissions.
     *
     * @return
     */
    String[] getAllClassifications();

    /**
     * Return all the potential SCI values based on the policy and the
     * requesting users permissions.
     *
     * @return
     */
    String[] getAllSCI();

    /**
     * Return all the potential SAP values based on the policy and the
     * requesting users permissions.
     *
     * @return
     */
    String[] getAllSAP();

    /**
     * Return all the potential FGI country codes based on the policy and the
     * requesting users permissions.
     *
     * @return
     */
    String[] getAllFGICountries();

    /**
     * Return all the AEA settings based on the policy and the requesting users
     * permissions.
     *
     * @return
     */
    String[] getAllAEA();

    /**
     * Return all the potential REL country codes based on the policy and the
     * requesting users permissions.
     *
     * @return
     */
    String[] getAllRelCountries();

    /**
     * Return all the potential DISPLAY TO country codes based on the policy and
     * the requesting users permissions.
     *
     * @return
     */
    String[] getAllDisplayCountries();

    /**
     * Return all the potential dissemination controls based on the policy and
     * the requesting users permissions.
     *
     * @return
     */
    String[] getAllDissemControls();

    /**
     * Return all the potential ACCM control words based on the policy and the
     * requesting users permissions.
     *
     * @return
     */
    String[] getAllACCM();
}
