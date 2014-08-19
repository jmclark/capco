
package org.geoint.capco.impl.policy;

import java.util.HashMap;
import java.util.Map;
import org.geoint.capco.CapcoException;
import org.geoint.capco.DuplicateComponentException;
import org.geoint.capco.marking.SapComponent;
import org.geoint.capco.marking.SecurityMarking;

/**
 *
 */
public class SAPComponentPolicy extends AbstractComponentPolicy<SapComponent>{

    protected final Map<String, SapComponent> programNameIndex = new HashMap<>();
   
    @Override
    public void addComponent(SapComponent component) throws CapcoException {
        if (programNameIndex.containsKey(component.getProgramName())) {
            throw new DuplicateComponentException(component, "Component "
                        + "banner marking is already present in policy.");
        }
        super.addComponent(component);
        programNameIndex.put(component.getProgramName(), component);
    }
    

    @Override
    public boolean isComponentString(String component) {
        if (super.isComponentString(component)) {
            return true;
        }
        if (programNameIndex.containsKey(component)) {
            return true;
        }
        return false;
    }

    @Override
    public SapComponent[] getAvailable(SecurityMarking marking) {
        
    }
   
}
