package org.geoint.lbac.marking.control;

/**
 * An control which has a potential third token.
 *
 * The alias control may be found using its portion, banner, or alias tokens in
 * the wild. This control is useful for categories such as with the SAP category
 * in the US security marking.
 */
public interface AliasSecurityControl extends SecurityControl {

    /**
     * Return the alias.
     *
     * @return
     */
    String getAlias();
}
