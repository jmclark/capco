package org.geoint.lbac.policy.control;

import org.geoint.lbac.marking.SecurityLabel;

/**
 *
 */
public interface CompartmentControlPolicy extends SecurityControlPolicy {

    SecurityLabel[] getSubcomponents();

}
