package org.geoint.lbac.impl.policy;

import java.util.Arrays;
import java.util.Objects;
import org.geoint.lbac.policy.control.CompartmentControlPolicy;

/**
 *
 */
public class CompartmentControlPolicyImpl implements CompartmentControlPolicy {

    private final String policyName;
    private final String categoryName;
    private final String compartmentName;
    private final String portion;
    private final String banner;
    private final SubCompartmentPolicy[] subcomponents;

    public CompartmentControlPolicyImpl(String policyName, String categoryName,
            String compartmentName, String portion, String banner,
            SubCompartmentPolicy... subcomponents) {
        this.policyName = policyName;
        this.categoryName = categoryName;
        this.compartmentName = compartmentName;
        this.portion = portion;
        this.banner = banner;
        this.subcomponents = subcomponents;
    }

    @Override
    public String getCompartment() {
        return compartmentName;
    }

    @Override
    public String getPolicyName() {
        return policyName;
    }

    @Override
    public String getCategory() {
        return categoryName;
    }

    @Override
    public String getPortion() {
        return portion;
    }

    @Override
    public String getBanner() {
        return banner;
    }

    @Override
    public SubCompartmentPolicy[] getSubcomponents() {
        return subcomponents;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.portion);
        hash = 83 * hash + Objects.hashCode(this.banner);
        hash = 83 * hash + Arrays.deepHashCode(this.subcomponents);
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
        final CompartmentControlPolicyImpl other = (CompartmentControlPolicyImpl) obj;
        if (!Objects.equals(this.portion, other.portion)) {
            return false;
        }
        if (!Objects.equals(this.banner, other.banner)) {
            return false;
        }
        if (!Arrays.deepEquals(this.subcomponents, other.subcomponents)) {
            return false;
        }
        return true;
    }

}
