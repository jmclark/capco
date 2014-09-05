package org.geoint.lbac.marking.control;

import org.geoint.lbac.marking.SecurityComponent;

/**
 * A Compartment is a SecurityControl which represents an data access control
 * restriction.
 *
 * Compartments are capable of being divided further, with subcomponents, based
 * on policy.
 */
public interface Compartment extends SecurityComponent {

    SecurityComponent[] getSubCompartments();
}
