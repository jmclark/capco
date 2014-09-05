package org.geoint.lbac.impl.marking.control;

import java.util.Objects;
import org.geoint.lbac.marking.control.AliasSecurityControl;
import org.geoint.lbac.policy.control.AliasControlPolicy;

/**
 *
 */
public class AliasSecurityControlImpl extends StandardSecurityControlImpl
        implements AliasSecurityControl {

    private final String alias;

    private AliasSecurityControlImpl(AliasControlPolicy policy, 
            String portion, String banner, String alias) {
        super(policy, portion, banner);
        this.alias = alias;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.alias);
        hash = 53 * hash + Objects.hashCode(this.getPortion());
        hash = 53 * hash + Objects.hashCode(this.getBanner());
        hash = 53 * hash + Objects.hashCode(this.getPolicy());
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
        final AliasSecurityControlImpl other = (AliasSecurityControlImpl) obj;
        if (!Objects.equals(this.alias, other.alias)) {
            return false;
        }
        if (!Objects.equals(this.getPortion(), other.getPortion())) {
            return false;
        }
        if (!Objects.equals(this.getBanner(), other.getBanner())) {
            return false;
        }
        if (!Objects.equals(this.getPolicy(), other.getPolicy())) {
            return false;
        }
        return true;
    }

}
