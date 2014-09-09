package org.geoint.lbac.impl.policy;

import java.util.Objects;
import org.geoint.lbac.policy.control.AliasControlPolicy;

/**
 *
 */
public class AliasControlPolicyImpl extends StandardControlPolicyImpl
        implements AliasControlPolicy {

    private final String alias;

    public AliasControlPolicyImpl(String policyName, String categoryName,
            String portion, String banner, String alias) {
        super(policyName, categoryName, portion, banner);
        this.alias = alias;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.alias);
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
        final AliasControlPolicyImpl other = (AliasControlPolicyImpl) obj;
        if (!Objects.equals(this.alias, other.alias)) {
            return false;
        }
        if (!Objects.equals(this.getPortion(), other.getPortion())) {
            return false;
        }
        if (!Objects.equals(this.getBanner(), other.getBanner())) {
            return false;
        }
        return true;
    }

}
