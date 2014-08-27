package org.geoint.capco.marking.component;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public class SciComponent extends MarkingComponent {

    private final SciCompartment[] compartments = new TreeSet<>();

    public SciComponent(String portion, String banner, SciCompartment... subcomponents) {
        super(portion, banner);
        this.compartments.addAll(Arrays.asList(subcomponents));
    }

    public Set<SciCompartment> getSubcomponents() {
        return compartments;
    }

    @Override
    public String getBanner() {
      
    }

    @Override
    public String getPortion() {
       
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.portion);
        hash = 79 * hash + Objects.hashCode(this.banner);
        hash = 79 * hash + Objects.hashCode(this.compartments);
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
        final SciComponent other = (SciComponent) obj;
        if (!Objects.equals(this.portion, other.portion)) {
            return false;
        }
        if (!Objects.equals(this.banner, other.banner)) {
            return false;
        }
        if (!Objects.equals(this.compartments, other.compartments)) {
            return false;
        }
        return true;
    }

}
