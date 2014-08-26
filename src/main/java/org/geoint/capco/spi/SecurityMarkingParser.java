package org.geoint.capco.spi;

import org.geoint.capco.impl.policy.SecurityPolicyImpl;
import org.geoint.capco.marking.InvalidSecurityMarkingException;
import org.geoint.capco.marking.SecurityMarking;

/**
 * SecurityMarking parser used by policies.
 *
 * @param <M>
 */
public abstract class SecurityMarkingParser<M extends SecurityMarking> {

    private final SecurityPolicyImpl policy;

    public SecurityMarkingParser(SecurityPolicyImpl policy) {
        this.policy = policy;
    }

    /**
     * Parses the marking within the context of an existing, overall, security
     * marking.
     *
     * @param context
     * @param marking
     * @return
     * @throws InvalidSecurityMarkingException
     */
    public abstract M parse(SecurityMarking context, String marking)
            throws InvalidSecurityMarkingException;

    /**
     * Parsers the security marking.
     *
     * @param marking
     * @return
     * @throws InvalidSecurityMarkingException
     */
    public abstract M parse(String marking)
            throws InvalidSecurityMarkingException;

    /**
     * Returns the policy context to parse the marking.
     *
     * @return
     */
    protected SecurityPolicyImpl policy() {
        return policy;
    }
}
