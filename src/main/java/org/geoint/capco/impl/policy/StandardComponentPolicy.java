package org.geoint.capco.impl.policy;

import java.util.HashMap;
import java.util.Map;
import org.geoint.capco.CapcoException;
import org.geoint.capco.DuplicateComponentException;
import org.geoint.capco.marking.component.MarkingComponent;

/**
 * Standard (generic) component policy features, used most often between the
 * components.
 *
 *
 * @param <C>
 */
public class StandardComponentPolicy<C extends MarkingComponent>
        extends AbstractComponentPolicy<C> {

    //component indicies
    protected final Map<String, C> bannerIndex = new HashMap<>();
    protected final Map<String, C> portionIndex = new HashMap<>();

    public StandardComponentPolicy(SecurityMarkingComponentExtractor<C> extractor) {
        super(extractor);
    }

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

//    @Override
//    public void validate(SecurityMarking marking) throws InvalidSecurityMarkingException {
//
//        //ensure the relevant component(s) in the marking are actually 
//        //part of the accepted components in the policy
//        final C[] markingComponents = componentExtractor.extract(marking);
//        for (C c : markingComponents) {
//            if (!components.contains(c)) {
//                StringBuilder sb = new StringBuilder();
//                sb.append("Invalid component '")
//                        .append(c.getBanner())
//                        .append("' found in marking.");
//                throw new InvalidSecurityMarkingException(
//                        marking.toString(),
//                        sb.toString());
//            }
//        }
//
//        //validate against registered restrictions
//        for (ComponentRestriction r : restrictions) {
//            r.validate(marking);
//        }
//    }
}
