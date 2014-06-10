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
     * Set the owning country of the marking, the default is set by the policy.
     * <p>
     * Setting this value may change the resultant type of security marking (ie
     * {@link USSecurityMarking}, {@link ForeignSecurityMarking}, or
     * {@link JointSecurityMarking}).
     *
     * @param countryCode
     * @return
     */
    SecurityMarkingBuilder setOwningCountry(String countryCode);

    /**
     * Add a contributing country.
     * <p>
     * Adding a contributing country will likely change the type of security
     * marking to {@link JointSecurityMarking}.
     *
     * @param countryCode
     * @return
     */
    SecurityMarkingBuilder addContributingCountry(String... countryCode);

    /**
     * Sets the base classification of the security marking.
     *
     * @param classificiation
     * @return
     */
    SecurityMarkingBuilder setClassification(String classificiation);

    /**
     * Adds a Sensitive Compartmented Information (SCI) control system.
     *
     * @param sci
     * @return
     */
    SecurityMarkingBuilder addSCI(String... sci);

    /**
     * Adds a Special Access Program (SAP) control system.
     *
     * @param sap
     * @return
     */
    SecurityMarkingBuilder addSAP(String... sap);

    /**
     * Sets the Atomic Energy Agency program information.
     *
     * @param aea
     * @return
     */
    SecurityMarkingBuilder setAEA(String aea);

    /**
     * Adds a Foreign Government Information marking, indicating the country of
     * origin for the classified data.
     *
     * @param countryCode
     * @return
     */
    SecurityMarkingBuilder addFGICountry(String... countryCode);

    /**
     * Add a releasable country dissemination control.
     *
     * @param countryCode
     * @return
     */
    SecurityMarkingBuilder addRelCountry(String... countryCode);

    /**
     * Adds a DISPLAY ONLY country dissemination control.
     *
     * @param countryCode
     * @return
     */
    SecurityMarkingBuilder addDisplayCountry(String... countryCode);

    /**
     * Adds a generic dissemination control.
     *
     * @param countryCode
     * @return
     */
    SecurityMarkingBuilder addDissemControl(String... countryCode);

    /**
     * Adds an Alternative Compensatory Control Measures (ACCM) control.
     *
     * @param accm
     * @return
     */
    SecurityMarkingBuilder addACCM(String... accm);

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

}
