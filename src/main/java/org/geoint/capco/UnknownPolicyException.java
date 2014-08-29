package org.geoint.capco;

/**
 *
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

}
