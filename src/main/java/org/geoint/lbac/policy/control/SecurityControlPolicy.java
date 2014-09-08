package org.geoint.lbac.policy.control;

import org.geoint.lbac.marking.SecurityLabel;
import org.geoint.lbac.marking.control.SecurityControl;

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
     * @return
     */
    String getCategoryName();

    /**
     * Returns an instance of the control.
     *
     * @return
     */
    SecurityControl getControl();
}
