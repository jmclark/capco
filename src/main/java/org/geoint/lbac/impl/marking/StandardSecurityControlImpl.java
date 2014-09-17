package org.geoint.lbac.impl.marking;

import java.util.Objects;
import org.geoint.lbac.marking.Control;
import org.geoint.lbac.policy.ControlPolicy;

/**
 * Simple security control which provides nothing more than a holder for the
 * portion/banner tokens.
 * 
 * @param <P>
 */
public class StandardSecurityControlImpl<P extends ControlPolicy>
    implements Control {

    private final P policy;

    protected StandardSecurityControlImpl(P policy) {
        this.policy = policy;
    }

    public static StandardSecurityControlImpl instance(ControlPolicy policy) {
        return new StandardSecurityControlImpl(policy);

    }

    @Override
    public String getPath() {
        return policy.getPath();
    }

    @Override
    public P getPolicy() {
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
