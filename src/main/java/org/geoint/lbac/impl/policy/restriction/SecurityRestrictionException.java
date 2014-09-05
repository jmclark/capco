package org.geoint.lbac.impl.policy.restriction;

import org.geoint.lbac.policy.PolicyException;

/**
 * An action has been restricted by a policy restriction.
 * 
 * @see SecurityRestriction
 */
public class SecurityRestrictionException extends PolicyException {

    public SecurityRestrictionException(String policyName) {
        super(policyName);
    }

    public SecurityRestrictionException(String policyName, String message) {
        super(policyName, message);
    }

    public SecurityRestrictionException(String policyName, String message, Throwable cause) {
        super(policyName, message, cause);
    }

    public SecurityRestrictionException(String policyName, Throwable cause) {
        super(policyName, cause);
    }

}
