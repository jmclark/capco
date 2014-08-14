package org.geoint.capco.impl.policy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.geoint.capco.CapcoException;
import org.geoint.capco.DuplicateComponentException;
import org.geoint.capco.InvalidSecurityMarkingException;
import org.geoint.capco.marking.MarkingComponent;
import org.geoint.capco.marking.SecurityMarking;

/**
 * Container for a components policy settings
 *
 * @param <C>
 */
public abstract class ComponentPolicy<C extends MarkingComponent> {

    protected final Set<C> components = new HashSet<>();
    protected final Map<String, C> bannerIndex = new HashMap<>();
    protected final Map<String, C> portionIndex = new HashMap<>();
    protected final List<ComponentRestriction> restrictions = new ArrayList<>();

    //policy locks
    protected final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(false);
    protected final Lock readLock = rwl.readLock();
    protected final Lock writeLock = rwl.writeLock();

    public void addComponent(C component) throws CapcoException {
        writeLock.lock();
        try {
            final String banner = component.getBanner().intern();
            final String portion = component.getPortion().intern();
            if (bannerIndex.containsKey(banner)) {
                throw new DuplicateComponentException(component, "Component "
                        + "banner marking is already present in policy.");
            }
            if (portionIndex.containsKey(portion)) {
                throw new DuplicateComponentException(component, "Component "
                        + "portion marking is already present in policy.");
            }
            this.components.add(component);
            this.bannerIndex.put(component.getBanner().intern(), component);
            this.portionIndex.put(component.getPortion().intern(), component);
        } finally {
            writeLock.unlock();
        }

    }

    public void addRestriction(ComponentRestriction res) {
        writeLock.lock(); //need an exclusive read lock so using a write lock
        try {
            this.restrictions.add(res);
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Determines if the provides string is a stringified representation of the
     * component.
     *
     * @param component
     * @return
     */
    public abstract boolean isComponentString(String component);

    /**
     * Validates this components values of the provided SecurityMarking.
     *
     * @param marking
     * @throws InvalidSecurityMarkingException
     */
    public abstract void validate(SecurityMarking marking) throws InvalidSecurityMarkingException;

    /**
     * Returns the controls that are available, given the provided security
     * marking.
     *
     * @param marking if null, returns all controls
     * @return
     */
    public abstract C[] getAvailable(SecurityMarking marking);

}
