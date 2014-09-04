package org.geoint.capco.policy.restriction;

import org.geoint.capco.marking.SecurityComponent;
import org.geoint.capco.marking.SecurityMarking;

/**
 * A SecurityRestriction is defined by a security policy and is applied against the
 * context of a {@link SecurityMarking} instance.  A SecurityRestriction defines 
 * the restriction itself and, if the restriction is triggered for an action 
 * on the {@link SecurityMarking}, a {@link RestrictionStrategy} is returned 
 * to be executed to run in lue of the previous operation. 
 * 
 * for validation or attempts to
 * mutate a marking (add/remove components, including during construction of a
 * SecurityMarking).
 *
 */
public interface SecurityRestriction {

    RestrictionStrategy restrictAddition(SecurityMarking marking, 
            SecurityComponent component);

    RestrictionStrategy restrictRemoval(SecurityMarking marking, 
            SecurityComponent component);
}
