package org.geoint.lbac.impl.command;

import org.geoint.lbac.impl.marking.SecurityMarkingBuilderImpl;
import org.geoint.lbac.marking.SecurityComponent;
import org.geoint.lbac.marking.SecurityMarking;
import org.geoint.lbac.marking.control.Control;

/**
 * Removes a {@link SecurityComponent}.
 */
public class RemoveControlCommand implements MarkingChangeCommand {

    private final SecurityMarking context;
    private final Control component;

    public RemoveControlCommand(SecurityMarking context,
            Control component) {
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
