package org.geoint.lbac.impl.policy;

import org.geoint.lbac.impl.ComponentCache;
import org.geoint.lbac.impl.marking.control.StandardSecurityControlImpl;
import org.geoint.lbac.marking.control.SecurityControl;
import org.geoint.lbac.policy.control.SecurityControlPolicy;

/**
 *
 */
public class StandardControlPolicyImpl
        implements SecurityControlPolicy {

    private final String componentPath;
    private final String policyName;
    private final String portion;
    private final String banner;

    public StandardControlPolicyImpl(String policyName, String componentPath,
            String portion, String banner) {
        this.policyName = policyName;
        this.componentPath = componentPath;
        this.portion = portion;
        this.banner = banner;
    }

    @Override
    public String getPolicyName() {
        return policyName;
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
    public String toString() {
        return portion;
    }

    @Override
    public SecurityControl getComponent() {

        StandardSecurityControlImpl ctl
                = ComponentCache.get(StandardSecurityControlImpl.class, componentPath);
        if (ctl == null) {
            ctl = StandardSecurityControlImpl.instance(this);
            ComponentCache.put(ctl);
        }
        return ctl;
    }

}
