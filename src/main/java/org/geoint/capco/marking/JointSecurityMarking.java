package org.geoint.capco.marking;

import org.geoint.capco.marking.component.Country;
import org.geoint.capco.marking.SecurityMarking;

/**
 * Joint classification markings are used on information owned or produced by
 * more than one country/international organization.
 *
 */
public interface JointSecurityMarking extends SecurityMarking {

    String HEADER = "JOINT";

    /**
     * Return the list of countries that contributed intelligence.
     *
     * @return
     */
    Country[] getContributingCountries();

    /**
     * Return the releasable country dissemination controls
     *
     * @return
     */
    Country[] getRelCountries();

}
