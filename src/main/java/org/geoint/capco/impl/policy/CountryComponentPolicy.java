package org.geoint.capco.impl.policy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.geoint.capco.CapcoException;
import org.geoint.capco.marking.CountryComponent;
import org.geoint.capco.marking.SecurityMarking;

/**
 *
 * @param <C>
 */
public class CountryComponentPolicy<C extends CountryComponent>
        implements ComponentPolicy<C> {

    //indicies
    private Map<String, List<String>> countries = new HashMap<>(); //key=country, value=list of assoc aliases
    private Map<String, String[]> aliasIndex = new HashMap<>(); //key=alias, value=countries
    
    
    @Override
    public void addComponent(C component) throws CapcoException {
        
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public C getComponent(String component) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public C[] getAvailable(SecurityMarking marking) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
