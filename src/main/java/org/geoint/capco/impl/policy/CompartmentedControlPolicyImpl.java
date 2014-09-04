package org.geoint.capco.impl.policy;

import org.geoint.capco.policy.control.*;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 */
public class CompartmentedControlPolicyImpl extends StandardControlPolicyImpl
        implements CompartmentedControlPolicy {

    private final CompartmentControlPolicyImpl[] compartments;
    private final CompartmentedControlFormat format;

    /**
     * Uses default formatting.
     *
     * @param policyName
     * @param categoryName
     * @param portion
     * @param banner
     * @param compartments
     */
    public CompartmentedControlPolicyImpl(String policyName, String categoryName,
            String portion, String banner,
            CompartmentControlPolicyImpl... compartments) {
        super(policyName, categoryName, portion, banner);
        this.format = new CompartmentedControlFormat();
        this.compartments = compartments;
    }

    public CompartmentedControlPolicyImpl(String policyName, String categoryName,
            String portion, String banner,
            CompartmentedControlFormat format,
            CompartmentControlPolicyImpl... compartments) {
        super(policyName, categoryName, portion, banner);
        this.format = format;
        this.compartments = compartments;
    }

    @Override
    public CompartmentControlPolicyImpl[] getCompartments() {
        return compartments;
    }

    @Override
    public CompartmentedControlFormat getFormat() {
        return format;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Arrays.deepHashCode(this.compartments);
        hash = 59 * hash + Objects.hashCode(this.getPortion());
        hash = 59 * hash + Objects.hashCode(this.getBanner());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CompartmentedControlPolicyImpl other = (CompartmentedControlPolicyImpl) obj;
        if (!Arrays.deepEquals(this.compartments, other.compartments)) {
            return false;
        }
        return true;
    }

}
