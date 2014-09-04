package org.geoint.capco.impl.marking.control;

import org.geoint.capco.impl.ComponentCache;
import org.geoint.capco.policy.control.CompartmentControlPolicy;

/**
 * A subcomponent is essentially a standard security control in terms of the 
 * structure and behavior of the control itself, but the cache keys are 
 * a little different.
 */
public class SubCompartment extends StandardSecurityControlImpl {

    private final String cacheKey;

    private SubCompartment(String cacheKey, CompartmentControlPolicy policy,
            String portion, String banner) {
        super(policy, portion, banner);
        this.cacheKey = cacheKey;
    }

    public static SubCompartment instance(CompartmentControlPolicy policy,
            String portion, String banner) {
        final String cacheKey = generateKey(policy, portion);
        SubCompartment cached = ComponentCache.get(SubCompartment.class,
                policy.getPolicyName(), cacheKey);
        if (cached == null) {
            cached = new SubCompartment(cacheKey, policy, portion, banner);
            ComponentCache.put(cached);
        }
        return cached;
    }

    @Override
    public String cacheKey() {
        return cacheKey;
    }

    private static String generateKey(CompartmentControlPolicy policy,
            String portion) {
        return policy.getCategoryName() + ":" + portion;
    }
}
