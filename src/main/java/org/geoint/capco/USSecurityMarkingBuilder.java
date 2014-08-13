
package org.geoint.capco;

/**
 *
 */
public interface USSecurityMarkingBuilder extends SecurityMarkingBuilder{

    /**
     * Adds a Sensitive Compartmented Information (SCI) control system.
     *
     * @param sci
     * @return
     * @throws org.geoint.capco.InvalidSecurityMarkingException
     */
    SecurityMarkingBuilder addSCI(String... sci)
            throws InvalidSecurityMarkingException;

    /**
     * Adds a Special Access Program (SAP) control system.
     *
     * @param sapNames either a SAP code word, program name, or assigned
     * digraph/trigraph
     * @return
     * @throws org.geoint.capco.InvalidSecurityMarkingException
     */
    SecurityMarkingBuilder addSAP(String... sapNames)
            throws InvalidSecurityMarkingException;

    /**
     * Sets the Atomic Energy Agency program information.
     *
     * @param aea
     * @return
     * @throws org.geoint.capco.InvalidSecurityMarkingException
     */
    SecurityMarkingBuilder setAEA(String aea)
            throws InvalidSecurityMarkingException;

    /**
     * Adds a Foreign Government Information marking, indicating the country of
     * origin for the classified data.
     *
     * @param countryCode
     * @return
     * @throws org.geoint.capco.InvalidSecurityMarkingException
     */
    SecurityMarkingBuilder addFGICountry(String... countryCode)
            throws InvalidSecurityMarkingException;

    /**
     * Add a releasable country dissemination control.
     *
     * @param countryCode
     * @return
     * @throws org.geoint.capco.InvalidSecurityMarkingException
     */
    SecurityMarkingBuilder addRelCountry(String... countryCode)
            throws InvalidSecurityMarkingException;

    /**
     * Adds a DISPLAY ONLY country dissemination control.
     *
     * @param countryCode
     * @return
     * @throws org.geoint.capco.InvalidSecurityMarkingException
     */
    SecurityMarkingBuilder addDisplayCountry(String... countryCode)
            throws InvalidSecurityMarkingException;

    /**
     * Adds a generic dissemination control.
     *
     * @param countryCode
     * @return
     * @throws org.geoint.capco.InvalidSecurityMarkingException
     */
    SecurityMarkingBuilder addDissemControl(String... controls)
            throws InvalidSecurityMarkingException;

    /**
     * Adds an Alternative Compensatory Control Measures (ACCM) control.
     *
     * @param accm
     * @return
     * @throws org.geoint.capco.InvalidSecurityMarkingException
     */
    SecurityMarkingBuilder addACCM(String... accm)
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
     * @throws InvalidSecurityMarkingException
     */
    public void setSpecialAccessChannelsOnly(boolean b)
            throws InvalidSecurityMarkingException;
}
