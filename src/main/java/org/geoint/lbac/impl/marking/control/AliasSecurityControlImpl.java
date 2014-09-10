package org.geoint.lbac.impl.marking.control;

import org.geoint.lbac.marking.control.AliasSecurityControl;
import org.geoint.lbac.policy.control.AliasControlPolicy;

/**
 *
 */
public class AliasSecurityControlImpl extends StandardSecurityControlImpl
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

    @Override
    public AliasControlPolicy getPolicy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
