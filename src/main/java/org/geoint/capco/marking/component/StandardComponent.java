
package org.geoint.capco.marking.component;

import java.util.Objects;

/**
 *
 */
public class StandardComponent implements MarkingComponent {

    private final String portion;
    private final String banner;
    
    public StandardComponent(String portion, String banner) {
        this.portion = portion.intern();
        this.banner = banner.intern();
    }

    @Override
    public String asPortion() {
        return portion;
    }

    @Override
    public String asBanner() {
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
        final StandardComponent other = (StandardComponent) obj;
        if (!Objects.equals(this.portion, other.portion)) {
            return false;
        }
        if (!Objects.equals(this.banner, other.banner)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(MarkingComponent o) {
        return this.portion.compareTo(o.asPortion());
    }

    @Override
    public String toString() {
        return portion;
    }
}
