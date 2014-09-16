package org.geoint.lbac.marking;

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
