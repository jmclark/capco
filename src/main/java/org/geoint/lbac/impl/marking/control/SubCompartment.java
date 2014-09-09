package org.geoint.lbac.impl.marking.control;

import org.geoint.lbac.impl.ComponentCache;
import org.geoint.lbac.policy.control.CompartmentControlPolicy;

/**
 * A subcomponent is essentially a standard security control in terms of the
 * structure and behavior of the control itself, but the cache keys are a little
 * different.
 */
public class SubCompartment extends StandardSecurityControlImpl {

    private SubCompartment(String cacheKey, CompartmentControlPolicy policy,
            String portion, String banner) {
        super(cacheKey, policy, portion, banner);
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

    protected static String generateKey(CompartmentControlPolicy policy,
            String portion) {
        return policy.getCategory() + ":" + policy.getCompartment() + ":" + portion;
    }
}
