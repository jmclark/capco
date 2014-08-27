package org.geoint.capco.impl.policy;

import org.geoint.capco.marking.InvalidSecurityMarkingException;
import org.geoint.capco.marking.component.MarkingComponent;

/**
 * Thrown when a marking component is not valid for a SecurityMarking, because
 * it violates a restriction on the SecurityPolicy.
 */
public class ComponentRestrictionException extends InvalidSecurityMarkingException {

    private final MarkingComponent invalidComponent;
    private final ComponentRestriction restriction;

    public ComponentRestrictionException(MarkingComponent invalidComponent,
            ComponentRestriction restriction, String marking, String message) {
        super(marking, message);
        this.invalidComponent = invalidComponent;
        this.restriction = restriction;
    }

    public ComponentRestrictionException(MarkingComponent invalidComponent,
            ComponentRestriction restriction, String marking, String message,
            Throwable cause) {
        super(marking, message, cause);
        this.invalidComponent = invalidComponent;
        this.restriction = restriction;
    }

    public ComponentRestrictionException(MarkingComponent invalidComponent,
            ComponentRestriction restriction, String marking, Throwable cause) {
        super(marking, cause);
        this.invalidComponent = invalidComponent;
        this.restriction = restriction;
    }

    public MarkingComponent getInvalidComponent() {
        return invalidComponent;
    }

    public ComponentRestriction getRestriction() {
        return restriction;
    }

}
