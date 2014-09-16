package org.geoint.lbac.sort;

import java.util.Comparator;
import org.geoint.lbac.marking.SecurityComponent;

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
