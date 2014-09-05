package org.geoint.lbac.policy.control;

/**
 *
 */
public interface WeightedControlPolicy
        extends SecurityControlPolicy, Comparable<WeightedControlPolicy> {

    public int getWeight();

}
