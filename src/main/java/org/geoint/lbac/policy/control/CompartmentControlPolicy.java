package org.geoint.lbac.policy.control;

/**
 *
 */
public interface CompartmentControlPolicy extends SecurityControlPolicy {

    String getCompartment();

    SecurityControlPolicy[] getSubcomponents();

}
