package org.geoint.capco;

/**
 * CAPCO SecurityMarking for US produced intelligence.
 */
public interface USSecurityMarking extends SecurityMarking {

    /**
     * Return the Sensitive Compartmented Information (SCI) components.
     *
     * @return
     */
    String[] getSCI();

    /**
     * Return the Special Access Program (SAP) components.
     *
     * @return
     */
    String[] getSAP();

    /**
     * Return Atomic Energy Agency program code.
     *
     * @return
     */
    String getAEA();

    /**
     * Return the Foreign Government Information marking
     *
     * @return
     */
    String[] getFGICountries();

    /**
     * Return the releasable country dissemination controls
     *
     * @return
     */
    String[] getRelCountries();

    /**
     * Return the display countries controls
     *
     * @return
     */
    String[] getDisplayCountries();

    /**
     * Return the dissemination controls
     *
     * @return
     */
    String[] getDissemControls();

    /**
     * Return the Alternative Compensatory Control Measures (ACCM) controls
     *
     * @return
     */
    String[] getAccm();
}
