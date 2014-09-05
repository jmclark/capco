package org.geoint.lbac.marking;

import org.geoint.lbac.LbacException;

/**
 *
 */
public class InvalidSecurityMarkingException extends LbacException {

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
