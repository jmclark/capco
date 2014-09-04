package org.geoint.capco.impl.policy;

import org.geoint.capco.impl.marking.CacheableSecurityComponent;

/**
 * A sub-compartment of a compartmentalized component.
 */
public class SubCompartment implements CacheableSecurityComponent {

    private final String policyName;
    private final String categoryName;
    private final String compartmentPortion;
    private final String portion;
    private final String banner;

    private SubCompartment(String policyName, String categoryName,
            String compartmentPortion, String portion, String banner) {
        this.policyName = policyName;
        this.categoryName = categoryName;
        this.compartmentPortion = compartmentPortion;
        this.portion = portion;
        this.banner = banner;
    }

    public SubCompartment instance(String policyName, String categoryName,
            String compartmentPortion, String portion, String banner) {
        return new SubCompartment(policyName, categoryName, compartmentPortion, portion, banner);
    }

    @Override
    public String getPolicyName() {
        return policyName;
    }

    /**
     * Returns a unique key based on the category name, compartment, and
     * sub-compartment.
     *
     * @return
     */
    @Override
    public String cacheKey() {
        return categoryName + ":" + compartmentPortion + ":" + portion;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCompartmentPortion() {
        return compartmentPortion;
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
