package org.geoint.capco.marking.control;

/**
 * A SecurityControl which has an additional weighting attribute, allowing the
 * control to be identified as a higher priority of another control in the same
 * category.
 *
 * This control is useful for categories such as classification, where
 * classification controls are tiers (ie UNCLASSIFIED, CONFIDENTIAL, SECRET, and
 * TOP SECRET).
 */
public interface WeightedSecurityControl extends SecurityControl {

    /**
     * Returns the weight of the control, used to compare the weight of other
     * controls within the same category.
     *
     * @return
     */
    int getWeight();
}
