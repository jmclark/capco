package org.geoint.capco.marking.component;

import org.geoint.capco.marking.SecurityMarking;

/**
 * A component of a {@link SecurityMarking}.
 *
 * A MarkingComponent must be immutable, and therefore thread safe.
 *
 * The MarkingComponent is comparable by its portion marking, ascending (natural
 * order of the String).
 */
public interface MarkingComponent extends Comparable<MarkingComponent> {

    /**
     * Returns the component in it's portion marking format.
     *
     * @return
     */
    public String asPortion();

    /**
     * Returns the component in it's banner format.
     *
     * @return
     */
    public String asBanner();
}
