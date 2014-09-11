package org.geoint.lbac.impl.policy;

import org.geoint.lbac.impl.ComponentCache;
import org.geoint.lbac.impl.marking.control.WeightedSecurityControlImpl;
import org.geoint.lbac.marking.control.WeightedSecurityControl;
import org.geoint.lbac.policy.control.WeightedControlPolicy;

/**
 *
 */
public class WeightedControlPolicyImpl extends StandardControlPolicyImpl
        implements WeightedControlPolicy {

    private final int weight;

    public WeightedControlPolicyImpl(String policyName, String componentPath,
            String portion, String banner, int weight) {
        super(policyName, componentPath, portion, banner);
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

    @Override
    public WeightedSecurityControl getComponent() {
        WeightedSecurityControl ctl
                = ComponentCache.get(WeightedSecurityControlImpl.class, this.getPath());
        if (ctl == null) {
            ctl = WeightedSecurityControlImpl.instance(this);
            ComponentCache.put(ctl);
        }
        return ctl;
    }

}
