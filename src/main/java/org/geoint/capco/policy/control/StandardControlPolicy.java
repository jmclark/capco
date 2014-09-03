package org.geoint.capco.policy.control;

import java.util.Objects;

/**
 * A control which simply contains a portion and banner token.
 */
public class StandardControlPolicy implements SecurityControlPolicy {

    private final String portion;
    private final String banner;

    public StandardControlPolicy(String portion, String banner) {
        this.portion = portion;
        this.banner = banner;
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
        return getPortion();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.portion);
        hash = 19 * hash + Objects.hashCode(this.banner);
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
        final StandardControlPolicy other = (StandardControlPolicy) obj;
        if (!Objects.equals(this.portion, other.portion)) {
            return false;
        }
        if (!Objects.equals(this.banner, other.banner)) {
            return false;
        }
        return true;
    }

}
