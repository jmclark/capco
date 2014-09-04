package org.geoint.capco.policy.control;

/**
 *
 */
public interface WeightedControlPolicy
        extends SecurityControlPolicy, Comparable<WeightedControlPolicy> {

    public int getWeight();

}
