package org.geoint.lbac.impl.marking.control;

import org.geoint.lbac.marking.AliasSecurityControl;
import org.geoint.lbac.policy.AliasControlPolicy;

/**
 *
 */
public class AliasSecurityControlImpl extends StandardSecurityControlImpl<AliasControlPolicy>
        implements AliasSecurityControl {

    private AliasSecurityControlImpl(AliasControlPolicy policy) {
        super(policy);
    }

    public static AliasSecurityControlImpl instance(AliasControlPolicy policy) {
        return new AliasSecurityControlImpl(policy);
    }

    @Override
    public String getAlias() {
        return getPolicy().getAlias();
    }

}
