package org.geoint.capco.marking;

import org.geoint.capco.marking.component.SciComponent;
import org.geoint.capco.marking.component.AeaComponent;
import org.geoint.capco.marking.component.CountryComponent;
import org.geoint.capco.marking.component.SapComponent;
import org.geoint.capco.marking.component.AccmComponent;
import org.geoint.capco.marking.component.DisseminationComponent;

/**
 * CAPCO SecurityMarking for US produced intelligence.
 */
public interface USSecurityMarking extends SecurityMarking {

    static final String SAP_BANNER_IDENTIFIER = "SPECIAL ACCESS REQUIRED-";
    static final String SAP_PORTION_IDENTIFIER = "SAR-";
    static final String REL_PORTION_IDENTIFIER = "REL ";
    static final String RELTO_IDENTIFIER = "REL TO ";
    static final String DISPLAY_IDENTIFIER = "DISPLAY ONLY ";
    static final String HVSACO_IDENTIFIER = "HVSACO";
    static final String FGI_IDENTIFIER = "FGI ";
    static final String ACCM_IDENTIFIER = "ACCM-";

    /**
     * Return the Sensitive Compartmented Information (SCI) components.
     *
     * @return
     */
    SciComponent[] getSCI();

    /**
     * Return the Special Access Program (SAP) components.
     *
     * @return
     */
    SapComponent[] getSAP();

    /**
     * Indicates if the material is "Handle via Special Access Channels Only".
     *
     * <i>Handle via Special Access Channels Only (HVSACO) is a control marking
     * used within the DoD SAP community to convey handling instructions; it is
     * not a classification level or dissemination control. HVSACO is applied to
     * non-SAP material (unclassified or classified) that exists within a SAP
     * environment and due to its subject or content warrants handling only
     * within SAP channels, amongst SAP cleared personnel. Marking guidance for
     * HVSACO material is conveyed in program classification guides.</i>
     *
     * @return
     */
    boolean isHVSACO();

    /**
     * Return Atomic Energy Agency program code.
     *
     * @return
     */
    AeaComponent getAEA();

    /**
     * Return the Foreign Government Information marking
     *
     * @return
     */
    CountryComponent[] getFGICountries();

    /**
     * Return the releasable country dissemination controls
     *
     * @return
     */
    CountryComponent[] getRelCountries();

    /**
     * Return the display countries controls
     *
     * @return
     */
    CountryComponent[] getDisplayCountries();

    /**
     * Return the dissemination controls
     *
     * @return
     */
    DisseminationComponent[] getDissemControls();

    /**
     * Return the Alternative Compensatory Control Measures (ACCM) controls
     *
     * @return
     */
    AccmComponent[] getAccm();
}
