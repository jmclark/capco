package org.geoint.capco.policy;

/**
 * Instructs on how to format a compartmentalized category.
 */
public class CompartmentedCategoryFormat extends CategoryFormat {

    public static final String DEFAULT_SUBCOMPONENT_SEPARATOR = " ";
    private final String subcomparmentSeparator;
    private final SortOrder subcompartmentSortOrder;

    public CompartmentedCategoryFormat() {
        this.subcomparmentSeparator = DEFAULT_SUBCOMPONENT_SEPARATOR;
        this.subcompartmentSortOrder = DEFAULT_SORT_ORDER;
    }

    public CompartmentedCategoryFormat(String label, String labelSeparator,
            String controlSeparator, SortOrder categorySortOrder,
            String subcomparmentSeparator, SortOrder subcatSortOrder) {
        super(label, labelSeparator, controlSeparator, categorySortOrder);
        this.subcomparmentSeparator = subcomparmentSeparator;
        this.subcompartmentSortOrder = subcatSortOrder;
    }

    public String getSubcomparmentSeparator() {
        return subcomparmentSeparator;
    }

    public SortOrder getSubcompartmentSortOrder() {
        return subcompartmentSortOrder;
    }
}
