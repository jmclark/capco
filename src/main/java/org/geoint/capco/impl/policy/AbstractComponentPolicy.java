package org.geoint.capco.impl.policy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
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

    protected final Set<C> components = new HashSet<>();
    protected final SecurityMarkingComponentExtractor<C> extractor;

//    protected final List<ComponentRestriction> restrictions = new ArrayList<>();
    //policy locks
    protected final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(false);
    protected final Lock readLock = rwl.readLock();
    protected final Lock writeLock = rwl.writeLock();

    public AbstractComponentPolicy(SecurityMarkingComponentExtractor<C> extractor) {
        this.extractor = extractor;
    }

    /**
     * Returns component controls which aren't selected in the marking.
     *
     * @param marking
     * @return
     */
    @Override
    public C[] getAvailable(SecurityMarking marking) {
        final C[] assigned = extractor.extract(marking);
        Arrays.sort(assigned);
        List<C> available = new ArrayList<>();

        readLock.lock();
        try {
            componentLoop:
            for (C c : components) {
                if (Arrays.binarySearch(assigned, c) < 0) {
                    //component isn't already assigned
                    available.add(c); //not already assigned or member of an assigned alias
                }
            }
        } finally {
            readLock.unlock();
        }

        return (C[]) available.toArray();
    }
//    @Override
//    public void addRestriction(ComponentRestriction res) {
//        writeLock.lock(); //need an exclusive read lock so using a write lock
//        try {
//            this.restrictions.add(res);
//        } finally {
//            writeLock.unlock();
//        }
//    }
//    
//    @Override
//    public ComponentRestriction[] getRestrictions() {
//        readLock.lock();
//        try {
//            return (ComponentRestriction[]) restrictions.toArray();
//        } finally {
//            readLock.unlock();
//        }
//    }

    @Override
    public C[] getComponents() {
        readLock.lock();
        try {
            return (C[]) components.toArray();
        } finally {
            readLock.unlock();
        }
    }

//    protected C[] filterRestrictedComponents(SecurityMarking marking) {
//        readLock.lock();
//        try {
//            List<C> available = new ArrayList<>();
//            componentLoop:
//            for (C c : components) {
//                for (ComponentRestriction r : restrictions) {
//                    if (!r.isPermitted(marking, c)) {
//                        continue componentLoop;
//                    }
//                }
//                available.add(c);
//            }
//            return (C[]) available.toArray(new MarkingComponent[available.size()]);
//        } finally {
//            readLock.unlock();
//        }
//    }
}
