package org.geoint.capco.policy;

import org.geoint.capco.marking.SecurityLabel;

/**
 * Used with default settings.
 */
public class DefaultFormatSecurityLabel implements SecurityLabel {

    private final String portion;
    private final String banner;

    public DefaultFormatSecurityLabel(String portion, String banner) {
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
