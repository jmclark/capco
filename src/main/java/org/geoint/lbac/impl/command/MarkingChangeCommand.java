package org.geoint.lbac.impl.command;

import org.geoint.lbac.impl.marking.SecurityMarkingBuilderImpl;
import org.geoint.lbac.marking.SecurityMarking;
import org.geoint.lbac.marking.SecurityMarkingBuilder;

/**
 * A command to execute to modify a {@link SecurityMarking}.
 */
public interface MarkingChangeCommand {

    /**
     * Execute the command, applying the change to the
     * {@link SecurityMarkingBuilder}.
     *
     * @param builder
     * @throws CommandExecutionException if the execution of this command has
     * failed, indicating this change has <b>NOT</b> been applied.
     */
    void apply(SecurityMarkingBuilderImpl builder) throws CommandExecutionException;

    /**
     * Rollback the change made by this command.
     *
     * @param builder
     * @throws CommandExecutionException if the execution of this command has
     * failed, indicating this change has <b>NOT</b> been applied.
     */
    void rollback(SecurityMarkingBuilderImpl builder) throws CommandExecutionException;
}
