package org.geoint.capco;

/**
 *
 */
public interface JointSecurityMarkingBuilder extends USSecurityMarkingBuilder {

    /**
     * Add a contributing country.
     * <p>
     * Adding a contributing country will likely change the type of security
     * marking to {@link JointSecurityMarking}.
     *
     * @param countryCode
     * @return
     * @throws org.geoint.capco.InvalidSecurityMarkingException
     */
    JointSecurityMarkingBuilder addContributingCountry(String... countryCode)
            throws InvalidSecurityMarkingException;

    /**
     * Sets the base classification of the security marking.
     *
     * @param classificiation
     * @return
     * @throws org.geoint.capco.InvalidSecurityMarkingException
     */
    @Override
    JointSecurityMarkingBuilder setClassification(String classificiation)
            throws InvalidSecurityMarkingException;

    /**
     * Returns the available classifications based on the policy, the requesting
     * users permissions, and builders current state.
     *
     * @return
     */
    @Override
    String[] getAvailableClassifications();

    /**
     * Retrieve the SecurityMarking instance.
     *
     * @return
     * @throws InvalidSecurityMarkingException
     */
    JointSecurityMarking build() throws InvalidSecurityMarkingException;
}
