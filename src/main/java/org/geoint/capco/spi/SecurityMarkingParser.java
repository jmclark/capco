package org.geoint.capco.spi;

import org.geoint.capco.SecurityPolicy;
import org.geoint.capco.marking.SecurityMarking;

/**
 * SecurityMarking parser used by policies.
 *
 * @param <M>
 */
public abstract class SecurityMarkingParser<M extends SecurityMarking> {

    private final SecurityPolicy policy;

    public SecurityMarkingParser(SecurityPolicy policy) {
        this.policy = policy;
    }

    /**
     * Parses the marking within the context of an existing, overall, security
     * marking.
     *
     * @param context
     * @param marking
     * @return
     */
    public abstract M parse(M context, String marking);

    /**
     * Parsers the security marking.
     *
     * @param marking
     * @return
     */
    public abstract M parse(String marking);

    /**
     * Returns the policy context to parse the marking.
     *
     * @return
     */
    protected SecurityPolicy policy() {
        return policy;
    }
}