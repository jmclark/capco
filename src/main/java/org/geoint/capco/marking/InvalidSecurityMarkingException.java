package org.geoint.capco.marking;

import org.geoint.capco.CapcoException;

/**
 *
 */
public class InvalidSecurityMarkingException extends CapcoException {

    private final String invaidMarking;

    public InvalidSecurityMarkingException(String invaidMarking) {
        this.invaidMarking = invaidMarking;
    }

    public InvalidSecurityMarkingException(String invaidMarking, String message) {
        super(message);
        this.invaidMarking = invaidMarking;
    }

    public InvalidSecurityMarkingException(String invaidMarking, String message, Throwable cause) {
        super(message, cause);
        this.invaidMarking = invaidMarking;
    }

    public InvalidSecurityMarkingException(String invaidMarking, Throwable cause) {
        super(cause);
        this.invaidMarking = invaidMarking;
    }

    public String getInvaidMarking() {
        return invaidMarking;
    }

}
