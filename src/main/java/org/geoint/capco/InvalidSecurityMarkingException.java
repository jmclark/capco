package org.geoint.capco;

/**
 * Thrown when a value cannot be converted into a policy-constrained
 * {@link SecurityMarking}.
 */
public class InvalidSecurityMarkingException extends Exception {

    public InvalidSecurityMarkingException() {
    }

    public InvalidSecurityMarkingException(String message) {
        super(message);
    }

    public InvalidSecurityMarkingException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSecurityMarkingException(Throwable cause) {
        super(cause);
    }

}
