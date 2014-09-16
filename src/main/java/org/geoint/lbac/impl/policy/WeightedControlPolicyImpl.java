package org.geoint.lbac.impl.policy;

import org.geoint.lbac.impl.LabelCache;
import org.geoint.lbac.impl.marking.control.WeightedSecurityControlImpl;
import org.geoint.lbac.marking.WeightedSecurityControl;
import org.geoint.lbac.policy.WeightedControlPolicy;

/**
 *
 */
public class WeightedControlPolicyImpl extends StandardControlPolicyImpl
        implements WeightedControlPolicy {

    private final int weight;

    public WeightedControlPolicyImpl(String componentPath,
            String portion, String banner, int weight) {
        super(componentPath, portion, banner);
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
                = LabelCache.get(WeightedSecurityControlImpl.class, this.getPath());
        if (ctl == null) {
            ctl = WeightedSecurityControlImpl.instance(this);
            LabelCache.put(ctl);
        }
        return ctl;
    }

}
