package org.geoint.lbac.impl.command;

import org.geoint.lbac.impl.marking.SecurityMarkingBuilderImpl;
import org.geoint.lbac.marking.SecurityComponent;

/**
 * Adds a {@link SecurityComponent}.
 */
public class AddComponentCommand implements MarkingChangeCommand {

    private final SecurityComponent component;

    public AddComponentCommand(SecurityComponent component) {
        this.component = component;
    }

    @Override
    public void apply(SecurityMarkingBuilderImpl builder) throws CommandExecutionException {
        builder.doAdd(component);
    }

    @Override
    public void rollback(SecurityMarkingBuilderImpl builder) throws CommandExecutionException {
        builder.doRemove(component);
    }

}
