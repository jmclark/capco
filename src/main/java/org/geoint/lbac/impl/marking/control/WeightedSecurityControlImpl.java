package org.geoint.lbac.impl.marking.control;

import org.geoint.lbac.marking.WeightedSecurityControl;
import org.geoint.lbac.policy.WeightedControlPolicy;

/**
 *
 */
public class WeightedSecurityControlImpl
        extends StandardSecurityControlImpl<WeightedControlPolicy>
        implements WeightedSecurityControl {

    private WeightedSecurityControlImpl(WeightedControlPolicy policy) {
        super(policy);
    }

    public static WeightedSecurityControlImpl instance(
            WeightedControlPolicy policy) {
        return new WeightedSecurityControlImpl(policy);
    }

    @Override
    public int getWeight() {
        return getPolicy().getWeight();
    }

}
