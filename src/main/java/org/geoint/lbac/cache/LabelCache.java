package org.geoint.lbac.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.geoint.lbac.marking.Control;
import org.geoint.lbac.marking.SecurityComponent;
import org.geoint.lbac.marking.SecurityMarking;

/**
 * Simple, on-heap, non-synchronized (but thread safe) caching for cachable
 * label objects.
 *
 * Decided to use a custom cache, rather than a library because: - it was
 * important to me to minimize the number of dependencies (goal:none) - generic
 * caches have additional thread-safe protections that aren't really necessary
 * here, and result in unnecessary overhead
 *
 * This class is thread-safe.
 */
public class LabelCache {

    private final static Map<String, SecurityComponent> controls
            = new HashMap<>();  //not weak...we want it to stick around

    private final static Map<String, SecurityMarking> portions = new HashMap<>();
    private final static Map<String, SecurityMarking> banners = new HashMap<>();

    //we use locks instead of synchronization because reads significantly outnumber writes
    private final static ReentrantReadWriteLock controlLock = new ReentrantReadWriteLock();
    private final static Lock crl = controlLock.readLock();
    private final static Lock cwl = controlLock.writeLock();
    private final static ReentrantReadWriteLock markingLock = new ReentrantReadWriteLock();
    private final static Lock mrl = markingLock.readLock();
    private final static Lock mwl = markingLock.writeLock();

    /**
     * Retrieve a {@link SecurityMarking} from the cache.
     *
     *
     * @param marking
     * @return
     */
    public static SecurityMarking get(String marking) {
        mrl.lock();
        try {
            return (portions.containsKey(marking))
                    ? portions.get(marking)
                    : banners.get(marking);
        } finally {
            mrl.unlock();
        }
    }

    /**
     * Cache a {@link SecurityMarking}.
     *
     * @param marking
     */
    public static void put(SecurityMarking marking)
            throws NotCachableException {
        mwl.lock();
        try {
            //TODO reject instances that are annoted DoNotCache
            if (portions.containsKey(marking.getPortion())) {
                portions.put(marking.getPortion(), marking);
                banners.put(marking.getBanner(), marking);
            }
        } finally {
            mwl.lock();
        }
    }

    /**
     * Returns the cached component for the key.
     *
     * @param <T>
     * @param type
     * @param componentPath
     * @return cached component or null if it isn't cached
     */
    public static <T extends SecurityComponent> T
            get(Class<T> type, String componentPath) {
        crl.lock();
        try {
            return (T) controls.get(componentPath);
        } finally {
            crl.unlock();
        }
    }

    /**
     * Cache a component.
     *
     * @param control
     */
    public static void put(Control control)
            throws NotCachableException {
        cwl.lock();
        try {
            //TODO reject instances that are annoted DoNotCache
            controls.put(control.getPath(), control);
        } finally {
            cwl.unlock();
        }
    }

}
