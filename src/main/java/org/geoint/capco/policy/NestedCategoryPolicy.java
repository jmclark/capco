package org.geoint.capco.policy;

import org.geoint.capco.marking.SecurityCategory;

/**
 * A category which contains 1-deep subcategories.
 *
 * Nested categories are mostly an exception to the norm, where a single
 * {@link SecurityCategory} itself contains uniquely formatted/managed set of
 * controls. This is the situation for categories such as the
 * <i>dissemination</i> category within the US security marking, for example.
 */
public interface NestedCategoryPolicy extends CategoryPolicy {

    /**
     * Returns nested category policies.
     *
     * @return
     */
    SimpleCategoryPolicy[] getCategoryPolicies();
}
