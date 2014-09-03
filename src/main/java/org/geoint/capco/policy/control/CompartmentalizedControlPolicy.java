package org.geoint.capco.policy.control;

import org.geoint.capco.policy.CompartmentPolicy;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 */
public class CompartmentalizedControlPolicy extends StandardControlPolicy {

    private final CompartmentPolicy[] compartments;

    public CompartmentalizedControlPolicy(String portion, String banner,
            CompartmentPolicy... compartments) {
        super(portion, banner);
        this.compartments = compartments;
    }

    public CompartmentPolicy[] getCompartments() {
        return compartments;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Arrays.deepHashCode(this.compartments);
        hash = 59 * hash + Objects.hashCode(this.getPortion());
        hash = 59 * hash + Objects.hashCode(this.getBanner());
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
        final CompartmentalizedControlPolicy other = (CompartmentalizedControlPolicy) obj;
        if (!Arrays.deepEquals(this.compartments, other.compartments)) {
            return false;
        }
        if (!Objects.equals(
                this.getPortion(), other.getPortion())) {
            return false;
        }
        if (!Objects.equals(
                this.getBanner(), other.getBanner())) {
            return false;
        }
        return true;
    }

}
