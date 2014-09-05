package org.geoint.lbac.impl.command;

import org.geoint.lbac.impl.marking.SecurityMarkingBuilderImpl;
import org.geoint.lbac.marking.SecurityComponent;

/**
 * Removes a {@link SecurityComponent}.
 */
public class RemoveComponentCommand implements MarkingChangeCommand {

    private final SecurityComponent component;

    public RemoveComponentCommand(SecurityComponent component) {
        this.component = component;
    }

    @Override
    public void apply(SecurityMarkingBuilderImpl builder)
            throws CommandExecutionException {
        builder.doRemove(component);
    }

    @Override
    public void rollback(SecurityMarkingBuilderImpl builder)
            throws CommandExecutionException {
        builder.doAdd(component);
    }

}
