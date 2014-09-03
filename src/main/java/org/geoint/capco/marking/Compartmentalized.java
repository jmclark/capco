package org.geoint.capco.marking;

import org.geoint.capco.marking.control.Compartment;

/**
 * Indicates that a security category is compartmentalized.
 *
 * A compartmentalized category is one that may contain one or more security
 * compartments, which may be further compartmentalized. Access to
 * compartmentalized data is normally restricted to users with the need-to-know.
 *
 */
public interface Compartmentalized extends SecurityCategory {

    Compartment[] getCompartments();
}
