package org.geoint.lbac.policy;

/**
 *
 */
public class UnknownPolicyException extends PolicyException {

    public UnknownPolicyException(String policyName) {
        super(policyName);
    }

    public UnknownPolicyException(String policyName, String message) {
        super(policyName, message);
    }

    public UnknownPolicyException(String policyName, String message, Throwable cause) {
        super(policyName, message, cause);
    }

    public UnknownPolicyException(String policyName, Throwable cause) {
        super(policyName, cause);
    }

}
