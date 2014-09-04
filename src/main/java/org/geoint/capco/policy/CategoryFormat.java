package org.geoint.capco.policy;

import org.geoint.capco.impl.sort.AlphabeticalPortionComparator;
import org.geoint.capco.marking.SecurityLabel;

/**
 * Instructs how to format a security marking category.
 */
public class CategoryFormat {

    private final SecurityLabel label;
    private final SecurityLabel labelSeparator;
    private final SecurityLabel controlSeparator;
    private final SortOrder sortOrder;
    public static final String DEFAULT_PORTION_LABEL = "";
    public static final String DEFAULT_BANNER_LABEL = "";
    public static final String DEFAULT_LABEL_PORTION_SEPARATOR = "";
    public static final String DEFAULT_LABEL_BANNER_SEPARATOR = "";
    public static final String DEFAULT_CONTROL_PORTION_SEPARATOR = ", ";
    public static final String DEFAULT_CONTROL_BANNER_SEPARATOR = ", ";

    public CategoryFormat() {
        this.label = new DefaultFormatSecurityLabel(DEFAULT_PORTION_LABEL,
                DEFAULT_BANNER_LABEL);
        this.labelSeparator
                = new DefaultFormatSecurityLabel(DEFAULT_LABEL_PORTION_SEPARATOR,
                        DEFAULT_LABEL_BANNER_SEPARATOR);
        this.controlSeparator
                = new DefaultFormatSecurityLabel(DEFAULT_CONTROL_PORTION_SEPARATOR,
                        DEFAULT_CONTROL_BANNER_SEPARATOR);
        this.sortOrder
                = new SortOrder(new AlphabeticalPortionComparator(),
                        new AlphabeticalPortionComparator());
    }

    public CategoryFormat(SecurityLabel label, SecurityLabel labelSeparator,
            SecurityLabel separator, SortOrder order) {
        this.label = label;
        this.labelSeparator = labelSeparator;
        this.controlSeparator = separator;
        this.sortOrder = order;
    }

    public SecurityLabel getLabel() {
        return label;
    }

    public SecurityLabel getLabelSeparator() {
        return labelSeparator;
    }

    public SecurityLabel getControlSeparator() {
        return controlSeparator;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

}
