package org.geoint.capco;

/**
 * Programmatically, and dynamically, builds a {@link SecurityMarking}.
 * <p>
 * The builder allows the use to request the available options for marking
 * components based on the related {@link SecurityPolicy} and what has already
 * been set in the builder.
 */
public interface SecurityMarkingBuilder {

    


    /**
     * Sets the base classification of the security marking.
     *
     * @param classificiation
     * @return
     * @throws org.geoint.capco.InvalidSecurityMarkingException
     */
    SecurityMarkingBuilder setClassification(String classificiation)
            throws InvalidSecurityMarkingException;


    /**
     * Returns the available classifications based on the policy, the requesting
     * users permissions, and builders current state.
     *
     * @return
     */
    String[] getAvailableClassifications();

    /**
     * Returns all the available SCI controls based on the policy, the
     * requesting users permissions, and builders current state.
     *
     * @return
     */
    String[] getAvailableSCI();

    /**
     * Returns all the available SAP controls based on the policy, the
     * requesting users permissions, and builders current state.
     *
     * @return
     */
    String[] getAvailableSAP();

    /**
     * Returns all the available FGI countries based on the policy, the
     * requesting users permissions, and builders current state.
     *
     * @return
     */
    String[] getAvailableFGICountries();

    /**
     * Returns all the available AEA settings based on the policy, the
     * requesting users permissions, and builders current state.
     *
     * @return
     */
    String[] getAvailableAEA();

    /**
     * Returns all the available releasable country codes based on the policy,
     * the requesting users permissions, and builders current state.
     *
     * @return
     */
    String[] getAvailableRelCountries();

    /**
     * Returns all the available display country codes based on the policy, the
     * requesting users permissions, and builders current state.
     *
     * @return
     */
    String[] getAvailableDisplayCountries();

    /**
     * Returns all the available dissemination controls based on the policy, the
     * requesting users permissions, and builders current state.
     *
     * @return
     */
    String[] getAvailableDissemCountrols();

    /**
     * Returns all the available ACCM controls based on the policy, the
     * requesting users permissions, and builders current state.
     *
     * @return
     */
    String[] getAvailableACCM();

    /**
     * Retrieve the SecurityMarking instance.
     *
     * @return
     * @throws InvalidSecurityMarkingException
     */
    SecurityMarking build() throws InvalidSecurityMarkingException;
}
