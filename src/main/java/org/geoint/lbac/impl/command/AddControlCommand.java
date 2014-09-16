package org.geoint.lbac.impl.command;

import org.geoint.lbac.impl.marking.SecurityMarkingBuilderImpl;
import org.geoint.lbac.marking.SecurityComponent;
import org.geoint.lbac.marking.SecurityMarking;
import org.geoint.lbac.marking.Control;

/**
 * Adds a {@link SecurityComponent}.
 */
public class AddControlCommand implements MarkingChangeCommand {

    private final SecurityMarking context;
    private final Control component;

    public AddControlCommand(SecurityMarking context,
            Control component) {
        this.component = component;
        this.context = context;
    }

    @Override
    public void apply(SecurityMarkingBuilderImpl builder) throws CommandExecutionException {
        builder.doAdd(component);
    }

    @Override
    public SecurityMarking getContext() {
        return context;
    }

}
