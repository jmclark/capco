package org.geoint.capco.impl.policy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.geoint.capco.CapcoException;
import org.geoint.capco.marking.CountryComponent;
import org.geoint.capco.marking.SecurityMarking;

/**
 *
 * @param <C>
 */
public class CountryComponentPolicy<C extends CountryComponent>
        implements ComponentPolicy<C> {

    private Map<String, C> components = new HashMap<>();
    //indicies
    private Map<String, Set<String>> countries = new HashMap<>(); //key=country, value=list of assoc aliases
    private Map<String, String[]> aliasIndex = new HashMap<>(); //key=alias, value=countries

    @Override
    public void addComponent(C c) throws CapcoException {
        final String code = c.getCountry().getCode();
        
        if (components.containsKey(code)) {
            return;
        }
        components.put(code, c);

        final String[] alias = c.getCountry().getAlias();

        if (alias != null) {
            //this is a country alias
            aliasIndex.put(code, alias);

            //add the country reverse index
            for (String a : alias) {
                getCountryAssocAliases(a).add(code);
            }
        } else {
            //is not an alias
            //creates the reference in the index if not already there
            getCountryAssocAliases(code);
        }
    }

    @Override
    public void addRestriction(ComponentRestriction res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComponentRestriction[] getRestrictions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isComponentString(String component) {
        return countries.containsKey(component) || aliasIndex.containsKey(component);
    }

    @Override
    public C getComponent(String component) {
        return components.get(component);
    }

    @Override
    public C[] getAvailable(SecurityMarking marking) {
        
    }

    private Set<String> getCountryAssocAliases(String code) {
        if (!countries.containsKey(code)) {
            countries.put(code, new HashSet<String>());
        }
        return countries.get(code);
    }

}
