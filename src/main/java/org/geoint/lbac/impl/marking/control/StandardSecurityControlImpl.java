package org.geoint.lbac.impl.marking.control;

import java.util.Objects;
import org.geoint.lbac.impl.marking.CacheableSecurityComponent;
import org.geoint.lbac.marking.control.SecurityControl;
import org.geoint.lbac.policy.control.SecurityControlPolicy;

/**
 * Simple security control which provides nothing more than a holder for the
 * portion/banner tokens.
 */
public class StandardSecurityControlImpl
        implements SecurityControl, CacheableSecurityComponent {

    private final String portion;
    private final String banner;
    private final SecurityControlPolicy policy;

    public StandardSecurityControlImpl(SecurityControlPolicy policy,
            String portion, String banner) {
        this.portion = portion;
        this.banner = banner;
        this.policy = policy;
    }

    @Override
    public SecurityControlPolicy getPolicy() {
        return policy;
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
    public String getPolicyName() {
        return policy.getPolicyName();
    }

    /**
     * Uses the portion marking of the control as the key.
     *
     * @return
     */
    @Override
    public String cacheKey() {
        return portion;
    }

    @Override
    public String toString() {
        return getPortion();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.portion);
        hash = 73 * hash + Objects.hashCode(this.banner);
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
        if (!Objects.equals(this.portion, other.portion)) {
            return false;
        }
        if (!Objects.equals(this.banner, other.banner)) {
            return false;
        }
        if (!Objects.equals(this.policy, other.policy)) {
            return false;
        }
        return true;
    }

}
