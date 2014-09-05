package org.geoint.lbac.policy.control;

/**
 *
 */
public interface CompartmentedControlPolicy extends SecurityControlPolicy {

    CompartmentControlPolicy[] getCompartments();

    CompartmentedControlFormat getFormat();

}
