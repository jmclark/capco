package org.geoint.lbac.impl.policy;

/**
 * A sub-compartment of a compartmentalized component.
 */
public class SubCompartmentPolicy extends StandardControlPolicyImpl {

    public SubCompartmentPolicy(String policyName, String componentPath,
            String portion, String banner) {
        super(policyName, componentPath, portion, banner);
    }

}
