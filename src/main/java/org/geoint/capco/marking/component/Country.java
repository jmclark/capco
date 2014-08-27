package org.geoint.capco.marking.component;

/**
 * A CAPCO country marking is a code that represents a country or a group of
 * countries. When representing a group of countries, the listed countries will
 * be available as alias codes.
 */
public final class Country {

    private final String code;
    private final String[] alias;

    public Country(String code) {
        this.code = code;
        this.alias = null;
    }

    public String getCode() {
        return code;
    }

    public String[] getAlias() {
        return alias;
    }

}
