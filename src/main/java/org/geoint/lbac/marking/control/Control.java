package org.geoint.lbac.marking.control;

import org.geoint.lbac.marking.SecurityCategory;
import org.geoint.lbac.marking.SecurityComponent;
import org.geoint.lbac.policy.ControlPolicy;

/**
 * A value within a {@link SecurityCategory}.
 */
public interface Control extends SecurityComponent {

    /**
     * {@inheritDoc}
     * 
     * @return 
     */
    @Override
    ControlPolicy getPolicy();
    
}
