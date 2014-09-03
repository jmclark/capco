package org.geoint.capco.policy;

import org.geoint.capco.impl.sort.AlphabeticalPortionComparator;
import org.geoint.capco.marking.SecurityLabel;

/**
 * Instructs how to format a security marking category.
 */
public class CategoryFormat {

    private final SecurityLabel label;
    private final SecurityLabel labelSeparator;
    private final SecurityLabel separator;
    private final SortOrder sortOrder;
    public static final String DEFAULT_PORTION_LABEL = "";
    public static final String DEFAULT_BANNER_LABEL = "";
    public static final String DEFAULT_LABEL_PORTION_SEPARATOR = "";
    public static final String DEFAULT_LABEL_BANNER_SEPARATOR = "";
    public static final String DEFAULT_PORTION_SEPARATOR = ", ";
    public static final String DEFAULT_BANNER_SEPARATOR = ", ";

    public CategoryFormat() {
        this.label = new DefaultFormatSecurityLabel(DEFAULT_PORTION_LABEL,
                DEFAULT_BANNER_LABEL);
        this.labelSeparator
                = new DefaultFormatSecurityLabel(DEFAULT_LABEL_PORTION_SEPARATOR,
                        DEFAULT_LABEL_BANNER_SEPARATOR);
        this.separator
                = new DefaultFormatSecurityLabel(DEFAULT_PORTION_SEPARATOR,
                        DEFAULT_BANNER_SEPARATOR);
        this.sortOrder
                = new SortOrder(new AlphabeticalPortionComparator(),
                        new AlphabeticalPortionComparator());
    }

    public CategoryFormat(SecurityLabel label, SecurityLabel labelSeparator,
            SecurityLabel separator, SortOrder order) {
        this.label = label;
        this.labelSeparator = labelSeparator;
        this.separator = separator;
        this.sortOrder = order;
    }

    public SecurityLabel getLabel() {
        return label;
    }

    public SecurityLabel getLabelSeparator() {
        return labelSeparator;
    }

    public SecurityLabel getSeparator() {
        return separator;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

    /**
     * Used with default settings.
     */
    protected class DefaultFormatSecurityLabel implements SecurityLabel {

        private final String portion;
        private final String banner;

        protected DefaultFormatSecurityLabel(String portion, String banner) {
            this.portion = portion;
            this.banner = banner;
        }

        @Override
        public String getPortion() {
            return portion;
        }

        @Override
        public String getBanner() {
            return banner;
        }

    }
}
