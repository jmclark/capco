package org.geoint.lbac.impl.policy;

import java.util.Arrays;
import java.util.Objects;
import org.geoint.lbac.marking.SecurityLabel;
import org.geoint.lbac.policy.control.CompartmentControlPolicy;

/**
 *
 */
public class CompartmentControlPolicyImpl implements CompartmentControlPolicy {

    private final String policyName;
    private final String categoryName;
    private final String portion;
    private final String banner;
    private final SecurityLabel[] subcomponents;

    public CompartmentControlPolicyImpl(String policyName, String categoryName,
            String portion, String banner, SecurityLabel... subcomponents) {
        this.policyName = policyName;
        this.categoryName = categoryName;
        this.portion = portion;
        this.banner = banner;
        this.subcomponents = subcomponents;
    }

    @Override
    public String getPolicyName() {
        return policyName;
    }

    public String getCategoryName() {
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

    public SecurityLabel[] getSubcomponents() {
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
