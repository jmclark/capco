package org.geoint.capco.policy.control;

/**
 *
 */
public interface CompartmentedControlPolicy extends SecurityControlPolicy {

    CompartmentControlPolicy[] getCompartments();

    CompartmentedControlFormat getFormat();

}
