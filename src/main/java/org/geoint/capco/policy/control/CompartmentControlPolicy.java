package org.geoint.capco.policy.control;

import org.geoint.capco.marking.SecurityLabel;

/**
 *
 */
public interface CompartmentControlPolicy extends SecurityControlPolicy {

    SecurityLabel[] getSubcomponents();

}
