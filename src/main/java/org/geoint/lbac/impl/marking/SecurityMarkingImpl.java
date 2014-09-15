package org.geoint.lbac.impl.marking;

import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import org.geoint.lbac.impl.marking.control.CompartmentedSecurityControlImpl;
import org.geoint.lbac.impl.policy.SecurityPolicyImpl;
import org.geoint.lbac.impl.policy.SubCompartmentPolicy;
import org.geoint.lbac.marking.InvalidSecurityMarkingException;
import org.geoint.lbac.marking.SecurityCategory;
import org.geoint.lbac.marking.SecurityComponent;
import org.geoint.lbac.marking.SecurityMarking;
import org.geoint.lbac.marking.UnknownSecurityComponentException;
import org.geoint.lbac.marking.control.Compartment;
import org.geoint.lbac.marking.control.CompartmentedSecurityControl;
import org.geoint.lbac.marking.control.SecurityControl;
import org.geoint.lbac.policy.SecurityPolicy;
import org.geoint.lbac.policy.control.CompartmentControlPolicy;

/**
 *
 */
public class SecurityMarkingImpl implements SecurityMarking {

    private final SecurityPolicyImpl policy;
    private final Map<String, SecurityCategory> categories; //key = path
    private final String portion;
    private final String banner;

    public SecurityMarkingImpl(SecurityPolicyImpl policy,
            Map<String, SecurityCategory> categories,
            String portion, String banner) {
        this.policy = policy;
        this.categories = categories;
        this.portion = portion;
        this.banner = banner;
    }

    public static SecurityMarking generate(SecurityPolicyImpl policy,
            SecurityControl... newControls) {
        //all components to reduce iterations, we'll just return the root categories 
        //when we're done
        Map<String, SecurityComponent> components = new HashMap<>();

    }

    private static SecurityMarking generate(SecurityPolicyImpl policy,
            Map<String, SecurityCategory> categories,
            SecurityControl... controls) throws UnknownSecurityComponentException {
        StringBuilder portion = new StringBuilder();
        StringBuilder banner = new StringBuilder();

        //aggregate the controls approperiatly
        for (SecurityControl c : controls) {

            String[] path = c.getPath().split(SecurityComponent.PATH_SEPARATOR);

            //iterate over the path, attempting to find the last component
            //(a container or the control itself) is available
            //TODO try this in reverse order for optimization
            StringBuilder componentPath = new StringBuilder(path[0]); //skip policy itself
            SecurityComponent component = null;
            for (int p = 1; p < path.length; p++) {
                final String currentComponentName = path[p];
                componentPath.append(currentComponentName);
                final String currentPath = componentPath.toString();

                if (component == null) {
                    //this is the root category, see if we at least have that
                    component = categories.get(currentPath);
                } else {
                    //already descended into a category/control, look for sub-
                    //component

                    if (component instanceof CompartmentedSecurityControl) {
                        CompartmentedSecurityControlImpl ctrl
                                = (CompartmentedSecurityControlImpl) component;
                        for (Compartment cmpt : ctrl.getCompartments()) {
                            if (cmpt.getPath().contentEquals(currentPath)) {
                                component = cmpt;
                                break;
                            }
                        }
                        //compartment wasn't found, retreive a new one from the policy
                        CompartmentControlPolicy cp = (CompartmentControlPolicy) policy.getComponentPolicy(currentPath);
                        Compartment newCompartment = (Compartment) cp.getComponent();
                        
                        
                    } else if (component instanceof Compartment) {
                        Compartment compartment = (Compartment) component;
                        for (SecurityComponent sc : compartment.getSubCompartments()) {
                            if (sc.getPath().contentEquals(currentPath)) {
                                component = sc;
                                break;
                            }
                            //subcompartment wasn't found, retrieve from the policy
                            SubCompartmentPolicy cp = (SubCompartmentPolicy) policy.getComponentPolicy(currentPath);
                            component = cp.getComponent();
                        }
                    } else if (component instanceof SecurityControl) {
                        //non-compartmented security control   
                        SecurityControl ctrl = (SecurityControl) component;

                    } else if (component instanceof SimpleSecurityCategory) {
                        SimpleSecurityCategory cat
                                = (SimpleSecurityCategory) component;

                    } else if (component instanceof NestedSecurityCategory) {
                        NestedSecurityCategory cat
                                = (NestedSecurityCategory) component;

                    }
                }

            }
        }
        /*
        
         for (Entry<String, SecurityCategory> ctl : SecurityCategory.entrySet () 
         ) {
         String[] path = ctl.getKey().split(SecurityComponent.PATH_SEPARATOR);
         StringBuilder hPath = new StringBuilder(path[0]); //policy name root
         for (int p = 1; p < path.length; p++) { //skip 0, which is the policy name
         hPath.append(path[p]);
         }

         }
         */
    }

    /**
     * Creates another {@link SecurityMarking} based on the provided marking,
     * merging the additional controls to the marking without checking the
     * {@link SecurityPolicy}.
     *
     * This method is used internally, but may also be used by applications
     * (very carefully) that wishes to create markings not defined by a policy.
     *
     * @param marking
     * @param newControls
     * @return
     */
    public static SecurityMarking generate(SecurityMarkingImpl marking,
            SecurityControl... newControls) {
        Map<String, SecurityCategory> components = new HashMap<>(marking.controls);
        for (SecurityControl c : newControls) {
            components.put(c.getPath(), c);
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
    public boolean isPermitted(String marking)
            throws InvalidSecurityMarkingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SecurityMarking merge(SecurityMarking... markings)
            throws InvalidSecurityMarkingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SecurityMarking merge(String... markings)
            throws InvalidSecurityMarkingException {
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
