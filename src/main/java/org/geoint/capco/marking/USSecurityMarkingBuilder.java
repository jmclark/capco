package org.geoint.capco.marking;

/**
 *
 */
public interface USSecurityMarkingBuilder {

    /**
     * Sets the base classification of the security marking.
     *
     * @param classificiation
     * @return
     * @throws InvalidSecurityMarkingException
     */
    USSecurityMarkingBuilder setClassification(String classificiation)
            throws InvalidSecurityMarkingException;

    /**
     * Adds a Sensitive Compartmented Information (SCI) control system.
     *
     * @param sci
     * @return
     * @throws InvalidSecurityMarkingException
     */
    USSecurityMarkingBuilder addSCI(String... sci)
            throws InvalidSecurityMarkingException;

    /**
     * Adds a Special Access Program (SAP) control system.
     *
     * @param sapNames either a SAP code word, program name, or assigned
     * digraph/trigraph
     * @return
     * @throws InvalidSecurityMarkingException
     */
    USSecurityMarkingBuilder addSAP(String... sapNames)
            throws InvalidSecurityMarkingException;

    /**
     * Sets the Atomic Energy Agency program information.
     *
     * @param aea
     * @return
     * @throws InvalidSecurityMarkingException
     */
    USSecurityMarkingBuilder setAEA(String aea)
            throws InvalidSecurityMarkingException;

    /**
     * Adds a Foreign Government Information marking, indicating the country of
     * origin for the classified data.
     *
     * @param countryCode
     * @return
     * @throws InvalidSecurityMarkingException
     */
    USSecurityMarkingBuilder addFGICountry(String... countryCode)
            throws InvalidSecurityMarkingException;

    /**
     * Add a releasable country dissemination control.
     *
     * @param countryCode
     * @return
     * @throws InvalidSecurityMarkingException
     */
    USSecurityMarkingBuilder addRelCountry(String... countryCode)
            throws InvalidSecurityMarkingException;

    /**
     * Adds a DISPLAY ONLY country dissemination control.
     *
     * @param countryCode
     * @return
     * @throws InvalidSecurityMarkingException
     */
    USSecurityMarkingBuilder addDisplayCountry(String... countryCode)
            throws InvalidSecurityMarkingException;

    /**
     * Adds a generic dissemination control.
     *
     * @param controls
     * @return
     * @throws InvalidSecurityMarkingException
     */
    USSecurityMarkingBuilder addDissemControl(String... controls)
            throws InvalidSecurityMarkingException;

    /**
     * Adds an Alternative Compensatory Control Measures (ACCM) control.
     *
     * @param accm
     * @return
     * @throws InvalidSecurityMarkingException
     */
    USSecurityMarkingBuilder addACCM(String... accm)
            throws InvalidSecurityMarkingException;

    /**
     * Sets the security marking as "Handle via Special Access Channels Only"
     * (HVSACO).
     *
     * Handle via Special Access Channels Only (HVSACO) is a control marking
     * used within the DoD SAP community to convey handling instructions; it is
     * not a classification level or dissemination control. HVSACO is applied to
     * non-SAP material (unclassified or classified) that exists within a SAP
     * environment and due to its subject or content warrants handling only
     * within SAP channels, amongst SAP cleared personnel. Marking guidance for
     * HVSACO material is conveyed in program classification guides.
     *
     * @param b
     * @return
     * @throws InvalidSecurityMarkingException
     */
    public USSecurityMarkingBuilder setSpecialAccessChannelsOnly(boolean b)
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
    USSecurityMarking build() throws InvalidSecurityMarkingException;
}
