package org.geoint.lbac.impl.policy;

import org.geoint.lbac.cache.LabelCache;
import org.geoint.lbac.impl.marking.AliasSecurityControlImpl;
import org.geoint.lbac.marking.AliasSecurityControl;
import org.geoint.lbac.policy.AliasControlPolicy;

/**
 *
 */
public class AliasControlPolicyImpl
        extends StandardControlPolicyImpl
        implements AliasControlPolicy {

    private final String alias;

    public AliasControlPolicyImpl(String componentPath,
            String portion, String banner, String alias) {
        super(componentPath, portion, banner);
        this.alias = alias;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public AliasSecurityControl getComponent() {
        AliasSecurityControlImpl ctl
                = LabelCache.get(AliasSecurityControlImpl.class, this.getPath());
        if (ctl == null) {
            ctl = AliasSecurityControlImpl.instance(this);
            LabelCache.put(ctl);
        }
        return ctl;
    }
}
