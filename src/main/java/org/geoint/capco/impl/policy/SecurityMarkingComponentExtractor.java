package org.geoint.capco.impl.policy;

import org.geoint.capco.marking.MarkingComponent;
import org.geoint.capco.marking.SecurityMarking;

/**
 * Extracts the specific security marking component out of the SecurityMarking.
 *
 * @param <C>
 */
public interface SecurityMarkingComponentExtractor<C extends MarkingComponent> {

    C[] extract(SecurityMarking marking);

}
