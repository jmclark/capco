package org.geoint.capco.marking;

import java.util.Objects;

/**
 *
 */
public abstract class MarkingComponent {

    private final String portion;
    private final String banner;

    public MarkingComponent(String portion, String banner) {
        this.portion = portion.intern();
        this.banner = banner.intern();
    }

    public String getPortion() {
        return portion;
    }

    public String getBanner() {
        return banner;
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
