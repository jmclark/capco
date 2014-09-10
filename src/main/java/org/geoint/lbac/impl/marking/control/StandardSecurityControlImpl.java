package org.geoint.lbac.impl.marking.control;

import java.util.Objects;
import org.geoint.lbac.marking.control.SecurityControl;
import org.geoint.lbac.policy.control.SecurityControlPolicy;

/**
 * Simple security control which provides nothing more than a holder for the
 * portion/banner tokens.
 */
public class StandardSecurityControlImpl
    implements SecurityControl {

    private final SecurityControlPolicy policy;

    protected StandardSecurityControlImpl(SecurityControlPolicy policy) {
        this.policy = policy;
    }

    public static StandardSecurityControlImpl instance(SecurityControlPolicy policy) {
        return new StandardSecurityControlImpl(policy);

    }

    @Override
    public String getPath() {
        return policy.getPath();
    }

    @Override
    public SecurityControlPolicy getPolicy() {
        return policy;
    }

    @Override
    public String getPortion() {
        return policy.getPortion();
    }

    @Override
    public String getBanner() {
        return policy.getBanner();
    }

    @Override
    public String toString() {
        return getPortion();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.policy);
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
        final StandardSecurityControlImpl other = (StandardSecurityControlImpl) obj;
        if (!Objects.equals(this.policy, other.policy)) {
            return false;
        }
        return true;
    }
}
