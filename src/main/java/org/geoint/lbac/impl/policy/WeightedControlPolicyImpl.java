package org.geoint.lbac.impl.policy;

import org.geoint.lbac.policy.control.WeightedControlPolicy;

/**
 *
 */
public class WeightedControlPolicyImpl extends StandardControlPolicyImpl
        implements WeightedControlPolicy {

    private final int weight;

    public WeightedControlPolicyImpl(String policyName, String categoryName,
            String portion, String banner, int weight) {
        super(policyName, categoryName, portion, banner);
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(WeightedControlPolicy o) {
        return Integer.valueOf(weight).compareTo(o.getWeight());
    }

}
