
package org.geoint.capco.impl.policy;

import org.geoint.capco.marking.SecurityMarking;

/**
 *
 */
public interface ComponentRestriction {

    void validate(SecurityMarking marking);

}
