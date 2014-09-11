package org.geoint.lbac.marking.control;

import org.geoint.lbac.marking.SecurityCategory;
import org.geoint.lbac.marking.SecurityComponent;
import org.geoint.lbac.policy.control.SecurityControlPolicy;

/**
 * A value within a {@link SecurityCategory}.
 */
public interface SecurityControl extends SecurityComponent {

    /**
     * {@inheritDoc}
     * 
     * @return 
     */
    @Override
    SecurityControlPolicy getPolicy();
    
}
