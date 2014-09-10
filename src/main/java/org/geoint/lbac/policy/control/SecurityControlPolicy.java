package org.geoint.lbac.policy.control;

import org.geoint.lbac.marking.SecurityLabel;
import org.geoint.lbac.marking.control.SecurityControl;
import org.geoint.lbac.policy.SecurityComponentPolicy;

/**
 * Policy configuration for a SecurityMarking control.
 *
 */
public interface SecurityControlPolicy
        extends SecurityLabel, SecurityComponentPolicy {

    /**
     * {@inheritDoc}
     */
    @Override
    SecurityControl getComponent();
}
