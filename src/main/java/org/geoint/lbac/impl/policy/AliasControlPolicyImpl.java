package org.geoint.lbac.impl.policy;

import org.geoint.lbac.impl.ComponentCache;
import org.geoint.lbac.impl.marking.control.AliasSecurityControlImpl;
import org.geoint.lbac.marking.control.AliasSecurityControl;
import org.geoint.lbac.policy.control.AliasControlPolicy;

/**
 *
 */
public class AliasControlPolicyImpl
        extends StandardControlPolicyImpl
        implements AliasControlPolicy {

    private final String alias;

    public AliasControlPolicyImpl(String policyName, String componentPath,
            String portion, String banner, String alias) {
        super(policyName, componentPath, portion, banner);
        this.alias = alias;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public AliasSecurityControl getComponent() {
        AliasSecurityControlImpl ctl
                = ComponentCache.get(AliasSecurityControlImpl.class, this.getPath());
        if (ctl == null) {
            ctl = AliasSecurityControlImpl.instance(this);
            ComponentCache.put(ctl);
        }
        return ctl;
    }
}
