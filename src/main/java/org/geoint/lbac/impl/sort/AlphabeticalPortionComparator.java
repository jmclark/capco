package org.geoint.lbac.impl.sort;

import java.util.Comparator;
import org.geoint.lbac.marking.SecurityComponent;

/**
 * Compares a {@link SecurityComponent} by it's portion marking using the
 * default String natural order (alphabetical, ascending).
 */
public class AlphabeticalPortionComparator implements Comparator<SecurityComponent> {
    
    @Override
    public int compare(SecurityComponent o1, SecurityComponent o2) {
        return o1.getPortion().compareTo(o2.getPortion());
    }
    
}
