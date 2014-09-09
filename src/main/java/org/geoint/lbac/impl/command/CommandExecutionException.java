package org.geoint.lbac.impl.command;

import org.geoint.lbac.LbacException;

/**
 * An exception thrown when a command fails to execute properly.
 */
public class CommandExecutionException extends LbacException {

    private final MarkingChangeCommand command;

    public CommandExecutionException(MarkingChangeCommand command,
            String message, Throwable cause) {
        super(message, cause);
        this.command = command;
    }

    public CommandExecutionException(MarkingChangeCommand command,
            Throwable cause) {
        super(cause);
        this.command = command;
    }

    public MarkingChangeCommand getCommand() {
        return command;
    }

}
