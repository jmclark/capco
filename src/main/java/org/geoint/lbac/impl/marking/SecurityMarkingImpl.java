package org.geoint.lbac.impl.marking;

import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import org.geoint.lbac.impl.policy.SecurityPolicyImpl;
import org.geoint.lbac.marking.InvalidSecurityMarkingException;
import org.geoint.lbac.marking.SecurityCategory;
import org.geoint.lbac.marking.SecurityComponent;
import org.geoint.lbac.marking.SecurityMarking;
import org.geoint.lbac.marking.control.SecurityControl;
import org.geoint.lbac.policy.CategoryPolicy;
import org.geoint.lbac.policy.SecurityPolicy;

/**
 *
 */
public class SecurityMarkingImpl implements SecurityMarking {

    private final SecurityPolicyImpl policy;
    private final Map<String, SecurityComponent> controls;
    private final String portion;
    private final String banner;

    public SecurityMarkingImpl(SecurityPolicyImpl policy, 
            Map<String, SecurityComponent> controls, 
            String portion, String banner) {
        this.policy = policy;
        this.controls = controls;
        this.portion = portion;
        this.banner = banner;
    }
    
    public static SecurityMarking generate(SecurityPolicyImpl policy,
            Map<String, SecurityControl> controls) {
        StringBuilder portion= new StringBuilder();
        StringBuilder banner = new StringBuilder();
        
        for (Entry<String, SecurityControl> ctl : controls.entrySet()) {
            String[] path = ctl.getKey().split(SecurityComponent.PATH_SEPARATOR);
            StringBuilder hPath = new StringBuilder(path[0]); //policy name root
            for (int p=1;p<path.length;p++) { //skip 0, which is the policy name
                hPath.append(path[p]);
            }
        }
    }

    @Override
    public SecurityPolicy getPolicy() {
        return policy;
    }

    @Override
    public byte[] asBytes() {
        try {
            return this.getPortion().getBytes("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            return this.getPortion().getBytes();
        }
    }

    @Override
    public boolean isPermitted(SecurityMarking marking) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isPermitted(String marking) throws InvalidSecurityMarkingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SecurityMarking merge(SecurityMarking... markings) throws InvalidSecurityMarkingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SecurityMarking merge(String... markings) throws InvalidSecurityMarkingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SecurityCategory[] getCategories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SecurityCategory getCategory(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPortion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getBanner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Sorts security components in the order that they must be processed
     */
    private class SecurityComponentComparator implements Comparator<SecurityComponent> {

        @Override
        public int compare(SecurityComponent o1, SecurityComponent o2) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
