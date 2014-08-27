package org.geoint.capco.marking.component;

/**
 *
 */
public abstract class CountryComponent extends MarkingComponent {

    private final Country country;

    public CountryComponent(Country country) {
        super(country.getCode(), country.getCode());
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

}
