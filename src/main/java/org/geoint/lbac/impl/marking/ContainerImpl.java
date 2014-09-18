package org.geoint.lbac.impl.marking;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.geoint.lbac.cache.DoNotCache;
import org.geoint.lbac.marking.ComponentContainer;
import org.geoint.lbac.marking.SecurityComponent;
import org.geoint.lbac.policy.ComponentPolicy;
import org.geoint.lbac.policy.ContainerPolicy;

/**
 * The container implementation is a unique security component in that is is NOT
 * cachable.
 *
 * This class IS thread-safe.
 *
 * @param <P> the policy type of the components contained within this container
 * @param <C> the type of security component contained with this component
 */
@DoNotCache
public class ContainerImpl<P extends ComponentPolicy, C extends SecurityComponent>
        implements ComponentContainer<P, C> {

    private final ContainerPolicy<P, C> policy;
    private String portionCache;
    private String bannerCache;
    private final Map<String, C> components; //key=path
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock rl = lock.readLock();
    private final Lock wl = lock.writeLock();

    private ContainerImpl(ContainerPolicy policy, C... components) {
        this.policy = policy;
        this.components = new HashMap<>();
        for (C c : components) {
            this.components.put(c.getPath(), c);
        }
    }

    /**
     * Creates an instance of the container with optional components
     *
     * @param <C>
     * @param <P>
     * @param policy
     * @param components
     * @return
     */
    public static <C extends SecurityComponent, P extends ComponentPolicy>
            ContainerImpl<P, C> instance(ContainerPolicy<P, C> policy, C... components) {
        return new ContainerImpl(policy);
    }

    @Override
    public ContainerPolicy getPolicy() {
        return policy;
    }

    @Override
    public String getPath() {
        return policy.getPath();
    }

    @Override
    public String getName() {
        return policy.getContainerName();
    }

    @Override
    public String getPortion() {
        rl.lock();
        try {
            if (portionCache == null) {
                rl.unlock(); //must release read lock..can't just elevate
                wl.lock();
                try {
                    //sigh, double check because I can't just elevate >:{}
                    if (portionCache == null) {
                        generateMarkings();
                    }
                } finally {
                    //downgrade to read lock
                    rl.lock();
                    wl.unlock();
                }
            }
            return portionCache;
        } finally {
            rl.unlock();
        }
    }

    @Override
    public String getBanner() {
        rl.lock();
        try {
            if (!isValidCache()) {
                rl.unlock(); //must release read lock..can't just elevate
                //no double check...very limited chance that we're calling this 
                //unnecessarly - no need to suffer the overhead on each read here
                generateMarkings(); 
                rl.lock();
            }
            return bannerCache;
        } finally {
            rl.unlock();
        }
    }

    /**
     * Checks if the portion/banner marking cache is valid.
     *
     * @return
     */
    private boolean isValidCache() {
        return portionCache == null || bannerCache == null;
    }

    /**
     * Generates the portion and banner markings for this container.
     *
     * Since the contents of the container may be changed, the portion and
     * banner markings can become invalid. To invalidate the markings, simply
     * set them to null.
     *
     * This method obtains a WRITE LOCK, which means any calling code MUST
     * ensure they release any read locks prior to calling this method.
     */
    private void generateMarkings() {
        wl.lock();
        try {
            StringBuilder p = new StringBuilder();
            StringBuilder b = new StringBuilder();
            p.append(policy.getBannerPrefix());
            b.append(policy.getBannerPrefix());
            
            //copy values into new collections so we don't mess with the map
            
            //we use two collections (one for portion, one for banner) due 
            //to generic contravarience of the Comparator
            //TODO can we do this without creating two sets?
            SortedSet<C> portionSorted = new TreeSet<>(policy.getPortionComparator());
            portionSorted.addAll(components.values());
            SortedSet<C> bannerSorted = new TreeSet<>(policy.getBannerComparator());
            bannerSorted.addAll(components.values());
            
            
            
        } finally {
            wl.unlock();
        }
    }

    @Override
    public C[] getComponents() {
        return (C[]) components.values().toArray();
    }

    @Override
    public C getComponent(String path) {
        return components.get(path);
    }

}
