package org.geoint.capco.policy;

/**
 * Instructs on how to format a compartmentalized category.
 */
public class CompartmentedCategoryFormat extends CategoryFormat {

    public static final String DEFAULT_SUBCOMPONENT_SEPARATOR = " ";
    private final String subcomparmentSeparator;

    public CompartmentedCategoryFormat() {
        this.subcomparmentSeparator = DEFAULT_SUBCOMPONENT_SEPARATOR;
    }

    public CompartmentedCategoryFormat(String label, String labelSeparator,
            String controlSeparator, String subcomparmentSeparator) {
        super(label, labelSeparator, controlSeparator);
        this.subcomparmentSeparator = subcomparmentSeparator;
    }

    public String getSubcomparmentSeparator() {
        return subcomparmentSeparator;
    }
}
