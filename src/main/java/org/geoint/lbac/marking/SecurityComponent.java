package org.geoint.lbac.marking;

import org.geoint.lbac.policy.ComponentPolicy;

/**
 * A component of a {@link SecurityMarking}.
 */
public interface SecurityComponent extends SecurityLabel {

    String PATH_SEPARATOR = "/";
    
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
     * Return the policy which defines this component.
     * 
     * @return 
     */
    ComponentPolicy getPolicy();
}
