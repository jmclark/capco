package org.geoint.capco.impl.policy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 */
public abstract class MarkingComponent {

    private String portion;
    private String banner;
    private final List<ComponentRestriction> restrictions = new ArrayList<>();

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public List<ComponentRestriction> getRestrictions() {
        return restrictions;
    }

    public void addRestriction(ComponentRestriction restriction) {
        this.restrictions.add(restriction);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.portion);
        hash = 31 * hash + Objects.hashCode(this.banner);
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
        final MarkingComponent other = (MarkingComponent) obj;
        if (!Objects.equals(this.portion, other.portion)) {
            return false;
        }
        if (!Objects.equals(this.banner, other.banner)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return banner;
    }
}
