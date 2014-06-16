package org.geoint.capco.impl.policy;

import org.geoint.capco.CapcoException;

/**
 * Thrown when a requested policy is not available.
 */
public class UnknownPolicyException extends CapcoException {

    private final String policyName;

    public UnknownPolicyException(String policyName) {
        this.policyName = policyName;
    }

    public UnknownPolicyException(String policyName, String message) {
        super(message);
        this.policyName = policyName;
    }

    public UnknownPolicyException(String policyName, String message, Throwable cause) {
        super(message, cause);
        this.policyName = policyName;
    }

    public UnknownPolicyException(String policyName, Throwable cause) {
        super(cause);
        this.policyName = policyName;
    }

    public String getPolicyName() {
        return policyName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown policy '").append(policyName).append("'. ");
        sb.append(super.toString());
        return sb.toString();
    }
}
