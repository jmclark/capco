package org.geoint.lbac.policy;

import org.geoint.lbac.policy.control.SecurityControlPolicy;

/**
 * A category which directly contains controls.
 *
 * A SimpleCategoryPolicy is needed for most categories, where a single policy
 * defines the way controls are managed and formatted within the component.
 *
 */
public interface SimpleCategoryPolicy extends CategoryPolicy {

    /**
     * Returns the {@link SecurityControlPolicy} instances that can be used with
     * this category.
     *
     * @return
     */
    SecurityControlPolicy[] getControlPolicies();

    /**
     * Return a {@link SecurityControlPolicy} for the provided value for this
     * category.
     *
     * The passed control value can be of any "indexable" attribute of the
     * control, such as the portion or banner marking.
     *
     * @param controlValue
     * @return
     */
    SecurityControlPolicy getControlPolicy(String controlValue);
}
