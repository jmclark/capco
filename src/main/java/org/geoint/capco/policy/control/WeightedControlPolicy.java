package org.geoint.capco.policy.control;

import java.util.Objects;

/**
 * A StandardControlPolicy which has an additional weighting attribute, allowing
 * a control to be identified as a higher priority of another control in the
 * same category.
 *
 * This control is useful for categories such as classification, where
 * classification controls are tiers (ie UNCLASSIFIED, CONFIDENTIAL, SECRET, and
 * TOP SECRET).
 */
public class WeightedControlPolicy extends StandardControlPolicy
        implements Comparable<WeightedControlPolicy> {

    private final int weight;

    public WeightedControlPolicy(String portion, String banner, int weight) {
        super(portion, banner);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(WeightedControlPolicy other) {
        return Integer.valueOf(weight).compareTo(other.weight);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.weight;
        hash = 11 * hash + Objects.hashCode(getPortion());
        hash = 11 * hash + Objects.hashCode(getBanner());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WeightedControlPolicy other = (WeightedControlPolicy) obj;
        if (this.weight != other.weight) {
            return false;
        }
        if (!this.getPortion().contentEquals(other.getPortion())) {
            return false;
        }
        if (!this.getBanner().contentEquals(other.getBanner())) {
            return false;
        }
        return true;
    }

}
