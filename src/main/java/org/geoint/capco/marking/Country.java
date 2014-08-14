package org.geoint.capco.marking;

/**
 *
 */
public final class Country {

    private final String code;
    private final String name;

    public Country(String code, String name) {
        this.code = code.intern();
        this.name = name.intern();
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
