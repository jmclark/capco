package org.geoint.capco.policy;

import org.geoint.capco.CapcoException;

/**
 *
 */
public class PolicyException extends CapcoException {

    private final String policyName;

    public PolicyException(String policyName) {
        this.policyName = policyName;
    }

    public PolicyException(String policyName, String message) {
        super(message);
        this.policyName = policyName;
    }

    public PolicyException(String policyName, String message, Throwable cause) {
        super(message, cause);
        this.policyName = policyName;
    }

    public PolicyException(String policyName, Throwable cause) {
        super(cause);
        this.policyName = policyName;
    }

    public String getPolicyName() {
        return policyName;
    }

}
