package org.geoint.lbac.impl.command;

import org.geoint.lbac.impl.marking.SecurityMarkingBuilderImpl;

/**
 * Wraps one or more {@link MarkingChangeCommand} to be executed as a single
 * coherent change.
 */
public class CompositeMarkingChangeCommand implements MarkingChangeCommand {

    private final MarkingChangeCommand[] commands;

    public CompositeMarkingChangeCommand(MarkingChangeCommand[] commands) {
        this.commands = commands;
    }

    @Override
    public void apply(SecurityMarkingBuilderImpl builder)
            throws CommandExecutionException {
        int position = 0;
        try {
            for (int i = 0; i < position; i++) {
                MarkingChangeCommand cmd = commands[i];
                cmd.apply(builder);
            }
        } catch (CommandExecutionException ex) {
            //rollback previous changes of those commands that did 
            //successfully execute
            try {
                for (int i = position; i >= 0; i--) {
                    commands[i].rollback(builder);
                }
            } catch (CommandExecutionException e) {
                //should never get here---only in impropoerly configured policies
                throw new CommandExecutionException(this, "Unable to complete "
                        + "rollback on changes that were made in composite.", e);
            }
        }
    }

    @Override
    public void rollback(SecurityMarkingBuilderImpl builder) 
            throws CommandExecutionException {
        //this method should never be called
        throw new CommandExecutionException(this, 
                "Unexpected call to the rollback method.");
    }

}
