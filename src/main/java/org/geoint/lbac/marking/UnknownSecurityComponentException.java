package org.geoint.lbac.marking;

import org.geoint.lbac.policy.SecurityPolicy;

/**
 * Thrown when an invalid component (according to the {@link SecurityPolicy}) is
 * attempted to be used.
 */
public class UnknownSecurityComponentException extends InvalidSecurityMarkingException {

    private final SecurityPolicy policy;
    private final String component;

    public UnknownSecurityComponentException(SecurityPolicy policy, String component,
            String invaidMarking) {
        super(invaidMarking);
        this.policy = policy;
        this.component = component;
    }

    public UnknownSecurityComponentException(SecurityPolicy policy, String component,
            String invaidMarking, String message) {
        super(invaidMarking, message);
        this.policy = policy;
        this.component = component;
    }

    public UnknownSecurityComponentException(SecurityPolicy policy, String component,
            String invaidMarking, String message, Throwable cause) {
        super(invaidMarking, message, cause);
        this.policy = policy;
        this.component = component;
    }

    public UnknownSecurityComponentException(SecurityPolicy policy, String component,
            String invaidMarking, Throwable cause) {
        super(invaidMarking, cause);
        this.policy = policy;
        this.component = component;
    }

    public SecurityPolicy getPolicy() {
        return policy;
    }

    public String getComponent() {
        return component;
    }

}
