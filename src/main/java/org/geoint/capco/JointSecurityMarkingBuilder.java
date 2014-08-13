
package org.geoint.capco;

/**
 *
 */
public interface JointSecurityMarkingBuilder  extends USSecurityMarkingBuilder{

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
    SecurityMarkingBuilder addContributingCountry(String... countryCode)
            throws InvalidSecurityMarkingException;
}
