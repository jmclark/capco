package org.geoint.capco;

import org.geoint.capco.marking.Country;

/**
 * Joint classification markings are used on information owned or produced by
 * more than one country/international organization.
 *
 */
public interface JointSecurityMarking extends USSecurityMarking {

    /**
     * Return the list of countries that contributed intelligence.
     *
     * @return
     */
    Country[] getContributingCountries();
}
