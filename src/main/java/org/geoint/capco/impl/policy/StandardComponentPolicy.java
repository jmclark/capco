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
 * Standard (generic) component policy features, used most often between the
 * components.
 *
 * @param <C>
 */
public abstract class StandardComponentPolicy<C extends MarkingComponent>
        implements ComponentPolicy<C> {

    protected final Set<C> components = new HashSet<>();
    private final SecurityMarkingComponentExtractor<C> componentExtractor;

    //component indicies
    protected final Map<String, C> bannerIndex = new HashMap<>();
    protected final Map<String, C> portionIndex = new HashMap<>();
    protected final List<ComponentRestriction> restrictions = new ArrayList<>();

    //policy locks
    protected final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(false);
    protected final Lock readLock = rwl.readLock();
    protected final Lock writeLock = rwl.writeLock();

    public StandardComponentPolicy(SecurityMarkingComponentExtractor<C> componentExtractor) {
        this.componentExtractor = componentExtractor;
    }

    @Override
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
    public void validate(SecurityMarking marking) throws InvalidSecurityMarkingException {

        //ensure the relevant component(s) in the marking are actually 
        //part of the accepted components in the policy
        final C[] markingComponents = componentExtractor.extract(marking);
        for (C c : markingComponents) {
            if (!components.contains(c)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid component '")
                        .append(c.getBanner())
                        .append("' found in marking.");
                throw new InvalidSecurityMarkingException(
                        marking.toString(),
                        sb.toString());
            }
        }

        //validate against registered restrictions
        for (ComponentRestriction r : restrictions) {
            r.validate(marking);
        }
    }

    /**
     * Extracts the specific security marking component out of the
     * SecurityMarking.
     *
     * @param <C>
     */
    public interface SecurityMarkingComponentExtractor<C extends MarkingComponent> {

        C[] extract(SecurityMarking marking);

    }
}
