package org.geoint.lbac.marking;

import org.geoint.lbac.policy.AliasControlPolicy;

/**
 * An control which has a potential third token.
 *
 * The alias control may be found using its portion, banner, or alias tokens in
 * the wild. This control is useful for categories such as with the SAP category
 * in the US security marking.
 */
public interface AliasSecurityControl extends Control {

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public AliasControlPolicy getPolicy();

    /**
     * Return the alias.
     *
     * @return
     */
    String getAlias();
}
