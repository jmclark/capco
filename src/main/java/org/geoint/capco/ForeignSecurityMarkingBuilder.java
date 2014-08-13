
package org.geoint.capco;

/**
 *
 */
public interface ForeignSecurityMarkingBuilder extends SecurityMarkingBuilder {
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
    SecurityMarkingBuilder setOwningCountry(String countryCode)
            throws InvalidSecurityMarkingException;
}
