package org.geoint.lbac.impl.command;

import org.geoint.lbac.impl.marking.SecurityMarkingBuilderImpl;
import org.geoint.lbac.marking.SecurityComponent;
import org.geoint.lbac.marking.SecurityMarking;

/**
 * Removes a {@link SecurityComponent}.
 */
public class RemoveComponentCommand implements MarkingChangeCommand {

    private final SecurityMarking context;
    private final SecurityComponent component;

    public RemoveComponentCommand(SecurityMarking context,
            SecurityComponent component) {
        this.context = context;
        this.component = component;
    }

    @Override
    public void apply(SecurityMarkingBuilderImpl builder)
            throws CommandExecutionException {
        builder.doRemove(component);
    }

    @Override
    public SecurityMarking getContext() {
        return context;
    }

}
