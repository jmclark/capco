package org.geoint.lbac.impl.policy.restriction;

import org.geoint.lbac.impl.command.MarkingChangeCommand;
import org.geoint.lbac.marking.SecurityMarking;

/**
 * A SecurityRestriction is defined by a security policy and is applied against
 * the context of a {@link SecurityMarking} instance.
 *
 * A SecurityRestriction defines the restriction itself and, if the restriction
 * is triggered for an action on the {@link SecurityMarking}, a
 * {@link RestrictionStrategy} is returned to be executed to run in lue of the
 * previous operation.
 *
 */
public interface SecurityRestriction {

    /**
     * Check if this command is restricted.
     *
     * @param command
     * @return the new command to execute, based on the restriction, or null if
     * the provided command is not restricted
     */
    MarkingChangeCommand restrict(MarkingChangeCommand command);
}
