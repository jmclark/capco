package org.geoint.capco.impl.policy;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class MarkingComponent {

    private String portion;
    private String banner;
    private final List<ComponentRestriction> restrictions = new ArrayList<>();

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public List<ComponentRestriction> getRestrictions() {
        return restrictions;
    }

    public void addRestriction(ComponentRestriction restriction) {
        this.restrictions.add(restriction);
    }

}
