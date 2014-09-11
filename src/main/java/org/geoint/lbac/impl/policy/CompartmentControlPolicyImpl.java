package org.geoint.lbac.impl.policy;

import org.geoint.lbac.policy.control.CompartmentControlPolicy;

/**
 *
 */
public class CompartmentControlPolicyImpl extends StandardControlPolicyImpl implements CompartmentControlPolicy {

    private final SubCompartmentPolicy[] subcomponents;

    public CompartmentControlPolicyImpl(String policyName, String componentPath,
            String portion, String banner,
            SubCompartmentPolicy... subcomponents) {
        super(policyName, componentPath, portion, banner);
        this.subcomponents = subcomponents;
    }

    @Override
    public SubCompartmentPolicy[] getSubcomponents() {
        return subcomponents;
    }
}
