package org.geoint.capco.policy.control;

import java.util.Objects;

/**
 * An control which has a potential third token.
 *
 * The alias control may be found using its portion, banner, or alias tokens in
 * the wild. This control is useful for categories such as with the SAP category
 * in the US security marking.
 */
public class AliasControlPolicy extends StandardControlPolicy {

    private final String alias;

    public AliasControlPolicy(String portion, String banner, String alias) {
        super(portion, banner);
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.alias);
        hash = 43 * hash + Objects.hashCode(this.getPortion());
        hash = 43 * hash + Objects.hashCode(this.getBanner());
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
        final AliasControlPolicy other = (AliasControlPolicy) obj;
        if (!Objects.equals(this.alias, other.alias)) {
            return false;
        }
        if (!Objects.equals(this.getPortion(), other.getPortion())) {
            return false;
        }
        if (!Objects.equals(this.getBanner(), other.getBanner())) {
            return false;
        }
        return true;
    }

}
