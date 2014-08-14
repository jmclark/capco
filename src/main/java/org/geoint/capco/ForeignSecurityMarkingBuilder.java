package org.geoint.capco;

/**
 *
 */
public interface ForeignSecurityMarkingBuilder {

    /**
     * Set the owning country of the marking, the default is set by the policy.
     * <p>
     * Setting this value may change the resultant type of security marking (ie
     * {@link USSecurityMarking}, {@link ForeignSecurityMarking}, or
     * {@link JointSecurityMarking}).
     *
     * @param countryCode
     * @return
     * @throws org.geoint.capco.InvalidSecurityMarkingException
     */
    ForeignSecurityMarkingBuilder setOwningCountry(String countryCode)
            throws InvalidSecurityMarkingException;

    /**
     * Sets the base classification of the security marking.
     *
     * @param classificiation
     * @return
     * @throws org.geoint.capco.InvalidSecurityMarkingException
     */
    ForeignSecurityMarkingBuilder setClassification(String classificiation)
            throws InvalidSecurityMarkingException;

    /**
     * Returns the available classifications based on the policy, the requesting
     * users permissions, and builders current state.
     *
     * @return
     */
    String[] getAvailableClassifications();

    /**
     * Retrieve the SecurityMarking instance.
     *
     * @return
     * @throws InvalidSecurityMarkingException
     */
    ForeignSecurityMarking build() throws InvalidSecurityMarkingException;
}
