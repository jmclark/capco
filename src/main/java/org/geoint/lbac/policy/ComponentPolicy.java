package org.geoint.lbac.policy;

import org.geoint.lbac.marking.SecurityComponent;

/**
 *
 * @param <C>
 */
public interface ComponentPolicy<C extends SecurityComponent> {

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
     * Returns an instance of the component.
     *
     * @return
     */
    C getComponent();

}
