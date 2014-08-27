package org.geoint.capco;

import org.geoint.capco.marking.component.MarkingComponent;

/**
 *
 */
public class DuplicateComponentException extends PolicyException {

    private final MarkingComponent component;

    public DuplicateComponentException(MarkingComponent component) {
        this.component = component;
    }

    public DuplicateComponentException(MarkingComponent component, String message) {
        super(message);
        this.component = component;
    }

    public DuplicateComponentException(MarkingComponent component, String message, Throwable cause) {
        super(message, cause);
        this.component = component;
    }

    public DuplicateComponentException(MarkingComponent component, Throwable cause) {
        super(cause);
        this.component = component;
    }

    public MarkingComponent getComponent() {
        return component;
    }

}
