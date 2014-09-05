package org.geoint.lbac.policy;

import java.util.Comparator;
import org.geoint.lbac.marking.SecurityComponent;

/**
 * The defined sort order used when formatting a {@link SecurityComponent}.
 * 
 * @param <C>
 */
public class SortOrder <C extends SecurityComponent> {

    private final Comparator<C> portion;
    private final Comparator<C> banner;

    public SortOrder(Comparator<C> portion,
            Comparator<C> banner) {
        this.portion = portion;
        this.banner = banner;
    }

    public Comparator<C> getPortion() {
        return portion;
    }

    public Comparator<C> getBanner() {
        return banner;
    }

}
