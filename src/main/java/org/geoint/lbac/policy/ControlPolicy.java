package org.geoint.lbac.policy;

import org.geoint.lbac.marking.SecurityLabel;
import org.geoint.lbac.marking.Control;

/**
 * Policy configuration for a SecurityMarking control.
 *
 * @param <C>
 */
public interface ControlPolicy <C extends Control>
        extends SecurityLabel, ComponentPolicy {

    /**
     * {@inheritDoc}
     */
    @Override
    C getComponent();
}
