package org.geoint.capco.impl.policy;

import org.geoint.capco.marking.MarkingComponent;
import org.geoint.capco.marking.SecurityMarking;

/**
 *
 */
public interface ComponentRestriction {

    /**
     * Validate the marking.
     *
     * @param marking
     */
    void validate(SecurityMarking marking);

    /**
     * Determine if the provided component is permitted to be added to the
     * provided marking.
     *
     * @param marking
     * @param c
     * @return
     */
    boolean isPermitted(SecurityMarking marking, MarkingComponent c);

}
