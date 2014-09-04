package org.geoint.capco.marking.control;

import org.geoint.capco.marking.SecurityCategory;
import org.geoint.capco.marking.SecurityComponent;
import org.geoint.capco.policy.control.SecurityControlPolicy;

/**
 * A value within a {@link SecurityCategory}.
 */
public interface SecurityControl extends SecurityComponent {

    SecurityControlPolicy getPolicy();
    
}
