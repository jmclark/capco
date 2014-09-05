package org.geoint.lbac.marking;

import org.geoint.lbac.policy.CategoryPolicy;

/**
 * A structural piece of a {@link SecurityMarking}.
 *
 */
public interface SecurityCategory extends SecurityComponent {

    /**
     * Return the name of the category.
     *
     * @return
     */
    String getName();

    /**
     * Return the underlying policy for the category.
     *
     * @return
     */
    CategoryPolicy getPolicy();


}
