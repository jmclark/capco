package org.geoint.capco.policy;

/**
 * Instructs how to format a security marking category.
 */
public class CategoryFormat {

    private final String label;
    private final String labelSeparator;
    private final String controlSeparator;
    public static final String DEFAULT_LABEL = "";
    public static final String DEFAULT_LABEL_SEPARATOR = "";
    public static final String DEFAULT_CONTROL_SEPARATOR = "";

    public CategoryFormat() {
        this.label = DEFAULT_LABEL;
        this.labelSeparator = DEFAULT_LABEL_SEPARATOR;
        this.controlSeparator = DEFAULT_CONTROL_SEPARATOR;
    }

    public CategoryFormat(String label, String labelSeparator, String controlSeparator) {
        this.label = label;
        this.labelSeparator = labelSeparator;
        this.controlSeparator = controlSeparator;
    }

    public String getLabel() {
        return label;
    }

    public String getLabelSeparator() {
        return labelSeparator;
    }

    public String getControlSeparator() {
        return controlSeparator;
    }

}
