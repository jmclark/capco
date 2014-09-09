package org.geoint.lbac.impl.policy;

import org.geoint.lbac.policy.control.SecurityControlPolicy;
import java.util.Objects;

/**
 *
 */
public class StandardControlPolicyImpl implements SecurityControlPolicy {

    private final String policyName;
    private final String categoryName;
    private final String portion;
    private final String banner;

    public StandardControlPolicyImpl(String policyName, String categoryName,
            String portion, String banner) {
        this.policyName = policyName;
        this.categoryName = categoryName;
        this.portion = portion;
        this.banner = banner;
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
    public String toString() {
        return portion;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.policyName);
        hash = 23 * hash + Objects.hashCode(this.categoryName);
        hash = 23 * hash + Objects.hashCode(this.portion);
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
        final StandardControlPolicyImpl other = (StandardControlPolicyImpl) obj;
        if (!Objects.equals(this.policyName, other.policyName)) {
            return false;
        }
        if (!Objects.equals(this.categoryName, other.categoryName)) {
            return false;
        }
        if (!Objects.equals(this.portion, other.portion)) {
            return false;
        }
        return true;
    }

}
