package org.geoint.lbac.impl.policy;

import java.util.Arrays;
import java.util.Objects;
import org.geoint.lbac.policy.control.CompartmentControlPolicy;

/**
 *
 */
public class CompartmentControlPolicyImpl extends StandardControlPolicyImpl implements CompartmentControlPolicy {

    private final String compartmentName;
    private final SubCompartmentPolicy[] subcomponents;

    public CompartmentControlPolicyImpl(String parentPath,
            String compartmentName, String portion, String banner,
            SubCompartmentPolicy... subcomponents) {
        super(parentPath, portion, banner);
        this.compartmentName = compartmentName;
        this.subcomponents = subcomponents;
    }

    @Override
    public String getCompartment() {
        return compartmentName;
    }

    @Override
    public SubCompartmentPolicy[] getSubcomponents() {
        return subcomponents;
    }
}
