package org.geoint.capco.policy;

import org.geoint.capco.marking.control.SecurityControl;

/**
 * A category which directly contains controls.
 *
 * A SimpleCategoryPolicy is needed for most categories, where a single policy
 * defines the way controls are managed and formatted within the component.
 *
 */
public interface SimpleCategoryPolicy extends CategoryPolicy {

    /**
     * Returns the {@link SecurityControl} instances that can be used with this
     * category.
     *
     * @return
     */
    SecurityControl[] getControls();
}
