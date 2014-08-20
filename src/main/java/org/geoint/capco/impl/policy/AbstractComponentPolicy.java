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
import org.geoint.capco.marking.MarkingComponent;
import org.geoint.capco.marking.SecurityMarking;

/**
 *
 * A security policy is internally thread-safe. If it's needed to lock the
 * entire SecurityPolicy, this must be done manually.
 *
 * @param <C>
 */
public abstract class AbstractComponentPolicy<C extends MarkingComponent>
        implements ComponentPolicy<C> {

    protected final Set<C> components = new HashSet<>();//component indicies
    protected final Map<String, C> bannerIndex = new HashMap<>();
    protected final Map<String, C> portionIndex = new HashMap<>();
    protected final List<ComponentRestriction> restrictions = new ArrayList<>();

    //policy locks
    protected final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(false);
    protected final Lock readLock = rwl.readLock();
    protected final Lock writeLock = rwl.writeLock();

    @Override
    public void addComponent(C component) throws CapcoException {
        writeLock.lock();
        try {
            final String banner = component.getBanner().intern();
            final String portion = component.getPortion().intern();
            if (portionIndex.containsKey(banner)) {
                throw new DuplicateComponentException(component, "Component "
                        + "banner marking is already present in policy.");
            }
            if (bannerIndex.containsKey(portion)) {
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

    @Override
    public void addRestriction(ComponentRestriction res) {
        writeLock.lock(); //need an exclusive read lock so using a write lock
        try {
            this.restrictions.add(res);
        } finally {
            writeLock.unlock();
        }
    }
    
    @Override
    public ComponentRestriction[] getRestrictions() {
        readLock.lock();
        try {
            return (ComponentRestriction[]) restrictions.toArray();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean isComponentString(String component) {
        readLock.lock();
        try {
            return portionIndex.containsKey(component)
                    || bannerIndex.containsKey(component);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public C getComponent(String component) {
        readLock.lock();
        try {
            if (portionIndex.containsKey(component)) {
                return portionIndex.get(component);
            } else if (bannerIndex.containsKey(component)) {
                return bannerIndex.get(component);
            }
            return null;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public C[] getAvailable(SecurityMarking marking) {
        return filterRestrictedComponents(marking);
    }

    protected C[] filterRestrictedComponents(SecurityMarking marking) {
        readLock.lock();
        try {
            List<C> available = new ArrayList<>();
            componentLoop:
            for (C c : components) {
                for (ComponentRestriction r : restrictions) {
                    if (!r.isPermitted(marking, c)) {
                        continue componentLoop;
                    }
                }
                available.add(c);
            }
            return (C[]) available.toArray(new MarkingComponent[available.size()]);
        } finally {
            readLock.unlock();
        }
    }
}
