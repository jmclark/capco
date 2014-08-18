package org.geoint.capco.marking;

import org.geoint.capco.CapcoException;

/**
 * Thrown when a value cannot be converted into a policy-constrained
 * {@link SecurityMarking}.
 */
public class InvalidSecurityMarkingException extends CapcoException {

    private final String marking;

    public InvalidSecurityMarkingException(String marking, String message) {
        super(message);
        this.marking = marking;
    }

    public InvalidSecurityMarkingException(String marking, String message, Throwable cause) {
        super(message, cause);
        this.marking = marking;
    }

    public InvalidSecurityMarkingException(String marking, Throwable cause) {
        super(cause);
        this.marking = marking;
    }

    public String getMarking() {
        return marking;
    }

}
