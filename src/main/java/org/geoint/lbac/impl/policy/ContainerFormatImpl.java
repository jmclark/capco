package org.geoint.lbac.impl.policy;

import org.geoint.lbac.impl.sort.AlphabeticalPortionComparator;
import org.geoint.lbac.marking.SecurityLabel;
import org.geoint.lbac.policy.ContainerFormat;
import org.geoint.lbac.policy.SortOrder;

/**
 *
 */
public class ContainerFormatImpl implements ContainerFormat {
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

    public ContainerFormatImpl() {
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

    public ContainerFormatImpl(SecurityLabel label, SecurityLabel labelSeparator,
            SecurityLabel separator, SortOrder order) {
        this.label = label;
        this.labelSeparator = labelSeparator;
        this.controlSeparator = separator;
        this.sortOrder = order;
    }

@Override
    public SecurityLabel getLabel() {
        return label;
    }

@Override
    public SecurityLabel getLabelSeparator() {
        return labelSeparator;
    }

@Override
    public SecurityLabel getControlSeparator() {
        return controlSeparator;
    }

@Override
    public SortOrder getSortOrder() {
        return sortOrder;
    }
}
