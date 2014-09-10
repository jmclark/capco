package org.geoint.lbac.impl.policy;

/**
 * A sub-compartment of a compartmentalized component.
 */
public class SubCompartmentPolicy extends StandardControlPolicyImpl {

    private final String compartmentPortion;

    public SubCompartmentPolicy(String parentPath,
            String portion, String banner, String compartmentPortion) {
        super(parentPath, portion, banner);
        this.compartmentPortion = compartmentPortion;
    }

    public String getCompartmentPortion() {
        return compartmentPortion;
    }

}
