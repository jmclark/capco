package org.geoint.lbac.impl.policy;

import java.util.Objects;
import org.geoint.lbac.impl.LabelCache;
import org.geoint.lbac.impl.marking.StandardSecurityControlImpl;
import org.geoint.lbac.marking.Control;
import org.geoint.lbac.policy.ControlPolicy;

/**
 *
 */
public class StandardControlPolicyImpl
        implements ControlPolicy {

    private final String componentPath;
    private final String portion;
    private final String banner;

    public StandardControlPolicyImpl(String componentPath, String portion,
            String banner) {
        this.componentPath = componentPath;
        this.portion = portion;
        this.banner = banner;
    }

    @Override
    public String getPath() {
        return componentPath;
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
    public Control getComponent() {

        StandardSecurityControlImpl ctl
                = LabelCache.get(StandardSecurityControlImpl.class, componentPath);
        if (ctl == null) {
            ctl = StandardSecurityControlImpl.instance(this);
            LabelCache.put(ctl);
        }
        return ctl;
    }

    @Override
    public String toString() {
        return componentPath;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.componentPath);
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
        if (!Objects.equals(this.componentPath, other.componentPath)) {
            return false;
        }
        return true;
    }

}
