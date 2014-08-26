package org.geoint.capco.impl.policy;

import java.util.HashMap;
import java.util.Map;
import org.geoint.capco.CapcoException;
import org.geoint.capco.DuplicateComponentException;
import org.geoint.capco.marking.SapComponent;

/**
 *
 */
public class SapComponentPolicy extends StandardComponentPolicy<SapComponent> {

    protected final Map<String, SapComponent> programNameIndex = new HashMap<>();

    public SapComponentPolicy(SecurityMarkingComponentExtractor<SapComponent> extractor) {
        super(extractor);
    }

    @Override
    public void addComponent(SapComponent component) throws CapcoException {
        writeLock.lock();
        try {
            if (programNameIndex.containsKey(component.getProgramName())) {
                throw new DuplicateComponentException(component, "Component "
                        + "banner marking is already present in policy.");
            }
            super.addComponent(component);
            programNameIndex.put(component.getProgramName(), component);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean isComponentString(String component) {
        if (isMultiple(component)) {
            return true;
        }

        readLock.lock();
        try {
            if (super.isComponentString(component)) {
                return true;
            }
            return programNameIndex.containsKey(component);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public SapComponent getComponent(String component) {
        if (isMultiple(component)) {
            return SapComponent.MULTIPLE;
        }

        readLock.lock();
        try {
            SapComponent c = super.getComponent(component);
            if (c != null) {
                return c;
            }
            return programNameIndex.get(component);
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Check if the SAP control name indicates there are 4 or more SAP controls.
     *
     * @param component
     * @return
     */
    private boolean isMultiple(String component) {
        return component.contentEquals(SapComponent.MULTIPLE.getPortion())
                || component.contentEquals(SapComponent.MULTIPLE.getBanner());
    }
}
