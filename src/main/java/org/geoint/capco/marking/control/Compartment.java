package org.geoint.capco.marking.control;

import org.geoint.capco.marking.SecurityComponent;

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
