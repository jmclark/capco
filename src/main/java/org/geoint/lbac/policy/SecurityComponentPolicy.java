package org.geoint.lbac.policy;

import org.geoint.lbac.marking.SecurityComponent;

/**
 *
 * @param <C>
 */
public interface SecurityComponentPolicy {

    /**
     * Return the unique hierarchical path of the category.
     *
     * The paths format is: policyName/category/[/container...]controlName
     *
     * Since categories can be nested, or controls complex (ie compartmented
     * controls) there may be 0 or more "container" names.
     *
     * @return
     */
    String getPath();

    /**
     * Return the name of the containing policy.
     *
     * @return
     */
    String getPolicyName();

    /**
     * Returns a new instance of the control
     *
     * @return
     */
    SecurityComponent getComponent();

}
