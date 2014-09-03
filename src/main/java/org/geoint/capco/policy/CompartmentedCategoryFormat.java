package org.geoint.capco.policy;

import org.geoint.capco.impl.sort.AlphabeticalPortionComparator;
import org.geoint.capco.marking.SecurityLabel;

/**
 * Instructs on how to format a compartmentalized category.
 */
public class CompartmentedCategoryFormat extends CategoryFormat {

    public static final String DEFAULT_SUBCOMP_PORTION_SEPARATOR = " ";
    public static final String DEFAULT_SUBCOMP_BANNER_SEPARATOR = " ";
    private final SecurityLabel subcomparmentSeparator;
    private final SortOrder subcompartmentSortOrder;

    public CompartmentedCategoryFormat() {
        this.subcomparmentSeparator
                = new DefaultFormatSecurityLabel(
                        DEFAULT_SUBCOMP_PORTION_SEPARATOR,
                        DEFAULT_SUBCOMP_BANNER_SEPARATOR);
        this.subcompartmentSortOrder
                = new SortOrder(new AlphabeticalPortionComparator(),
                        new AlphabeticalPortionComparator());
    }

    public CompartmentedCategoryFormat(SecurityLabel label,
            SecurityLabel labelSeparator, SecurityLabel controlSeparator,
            SortOrder categorySortOrder, SecurityLabel subcomparmentSeparator,
            SortOrder subcatSortOrder) {
        super(label, labelSeparator, controlSeparator, categorySortOrder);
        this.subcomparmentSeparator = subcomparmentSeparator;
        this.subcompartmentSortOrder = subcatSortOrder;
    }

    public SecurityLabel getSubcomparmentSeparator() {
        return subcomparmentSeparator;
    }

    public SortOrder getSubcompartmentSortOrder() {
        return subcompartmentSortOrder;
    }
}
