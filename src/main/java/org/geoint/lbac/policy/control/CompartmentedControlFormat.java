package org.geoint.lbac.policy.control;

import org.geoint.lbac.impl.sort.AlphabeticalPortionComparator;
import org.geoint.lbac.marking.SecurityLabel;
import org.geoint.lbac.policy.DefaultFormatSecurityLabel;
import org.geoint.lbac.policy.SortOrder;

/**
 * Instructs on how to format a compartmentalized category.
 */
public class CompartmentedControlFormat {

    public static final String DEFAULT_COMP_PORTION_SEPARATOR = " ";
    public static final String DEFAULT_COMP_BANNER_SEPARATOR = " ";
    public static final String DEFAULT_COMP_SUB_PORTION_SEPARATOR = "-";
    public static final String DEFAULT_COMP_SUB_BANNER_SEPARATOR = "-";
    private final SecurityLabel compartmentSeparator;
    private final SortOrder compartmentSortOrder;
    private final SecurityLabel subcomparmentSeparator;
    private final SortOrder subcompartmentSortOrder;

    public CompartmentedControlFormat() {
        this.compartmentSeparator
                = new DefaultFormatSecurityLabel(
                        DEFAULT_COMP_PORTION_SEPARATOR,
                        DEFAULT_COMP_BANNER_SEPARATOR);
        this.compartmentSortOrder
                = new SortOrder(new AlphabeticalPortionComparator(),
                        new AlphabeticalPortionComparator());

        this.subcomparmentSeparator
                = new DefaultFormatSecurityLabel(
                        DEFAULT_COMP_PORTION_SEPARATOR,
                        DEFAULT_COMP_BANNER_SEPARATOR);
        this.subcompartmentSortOrder
                = new SortOrder(new AlphabeticalPortionComparator(),
                        new AlphabeticalPortionComparator());
    }

    public CompartmentedControlFormat(SecurityLabel compartmentSeparator,
            SortOrder compartmentOrder, SecurityLabel subSeparator,
            SortOrder subSortOrder) {
        this.compartmentSeparator = compartmentSeparator;
        this.compartmentSortOrder = compartmentOrder;
        this.subcomparmentSeparator = subSeparator;
        this.subcompartmentSortOrder = subSortOrder;
    }

    public SecurityLabel getCompartmentSeparator() {
        return compartmentSeparator;
    }

    public SortOrder getCompartmentSortOrder() {
        return compartmentSortOrder;
    }

    public SecurityLabel getSubcomparmentSeparator() {
        return subcomparmentSeparator;
    }

    public SortOrder getSubcompartmentSortOrder() {
        return subcompartmentSortOrder;
    }

}
