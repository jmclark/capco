package org.geoint.capco.policy;

import org.geoint.capco.marking.SecurityLabel;
import org.geoint.capco.marking.SecurityMarking;

/**
 * Policy setting for a SecurityMarking category.
 */
public interface CategoryPolicy {

    /**
     * Returns the name of the category.
     *
     * @return
     */
    String getName();

    /**
     * Returns the optional category formatter to apply to the
     * {@link SecurityMarking} while rendering
     *
     * @return
     */
    CategoryFormat getFormatter();

    /**
     * Indicates if the security category is required in a valid
     * {@link SecurityMarking}.
     *
     * @return
     */
    boolean isRequired();

    /**
     * Indicates the maximum number of controls that can be applied to the
     * category at a time.
     *
     * This setting prevents more than the number of controls from being added
     * to a {@link SecurityMarking}. If you wish to allow to overflow, use the
     * overflow number.
     *
     * If set to any value less than 0 there is no max (the default).
     *
     * @return
     */
    int getMaxControls();

    /**
     * Returns the maximum number of controls that are allowed to be applied
     * before the category is over limits.
     *
     * When a category has been "overflowed" with controls, it will render the
     * configured overflow label instead of the components.
     *
     * @return
     */
    int getOverflowNum();

    /**
     * Returns the optional label to use in the {@link SecurityMarking} when the
     * number of applied controls exceeds the
     * {@link CategoryPolicy#getExceedNum()} of controls.
     *
     * @return
     */
    SecurityLabel getOverflowLabel();

}
