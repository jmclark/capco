package org.geoint.capco.impl.sort;

import java.util.Comparator;
import org.geoint.capco.marking.SecurityComponent;

/**
 * Compares a {@link SecurityComponent} by it's banner marking using the default
 * String natural order (alphabetical, ascending).
 */
public class AlphabeticalBannerComparator implements Comparator<SecurityComponent> {

    @Override
    public int compare(SecurityComponent o1, SecurityComponent o2) {
        return o1.getBanner().compareTo(o2.getBanner());
    }

}
