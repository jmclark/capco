package org.geoint.capco.marking.control;

import org.geoint.capco.marking.Compartmentalized;

/**
 * SecurityControl which provides compartmentalized (need-to-know) controls.
 *
 */
public interface CompartmentedSecurityControl
        extends SecurityControl, Compartmentalized {

}
