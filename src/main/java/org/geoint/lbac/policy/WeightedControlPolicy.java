package org.geoint.lbac.policy;

/**
 *
 */
public interface WeightedControlPolicy
        extends ControlPolicy, Comparable<WeightedControlPolicy> {

    public int getWeight();

}
