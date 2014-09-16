package org.geoint.lbac.marking.control;

/**
 * Control which provides compartmentalized (need-to-know) controls.
 *
 * A compartmentalized component is one that may contain one or more security
 * compartments, which may be further compartmentalized. Access to
 * compartmentalized data is normally restricted to users with the need-to-know.
 *
 */
public interface CompartmentedSecurityControl
        extends Control {

    Compartment[] getCompartments();
}
