package org.geoint.lbac.policy.control;

import org.geoint.lbac.marking.SecurityCategory;
import org.geoint.lbac.marking.SecurityLabel;
import org.geoint.lbac.policy.SecurityPolicy;

/**
 * Policy configuration for a SecurityMarking control.
 */
public interface SecurityControlPolicy extends SecurityLabel {

    /**
     * Return the name of the containing {@link SecurityPolicy} this control
     * policy applies.
     *
     * @return
     */
    String getPolicyName();

    /**
     * Return the name of the containing {@link SecurityCategory} this control
     * is associated with.
     *
     * Depending on the category type (simple or nested) and the control, the
     * category name may represent the positional hierarchy of the category. For
     * example, if the control is an AliasSecurityControl, which is can be added
     * to a simple category (root level) the category will simply be the
     * category name. However, if the same control is actually positioned within
     * a complex category, within a nested category, the name of the category
     * will be the root category name, separator, the nested category name. The
     * actual separator used is implementation specific.
     *
     * @return
     */
    String getCategory();

}
