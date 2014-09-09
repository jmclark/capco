package org.geoint.lbac.impl.marking.control;

import org.geoint.lbac.impl.ComponentCache;
import org.geoint.lbac.impl.marking.CacheableSecurityComponent;
import org.geoint.lbac.marking.control.Compartment;
import org.geoint.lbac.policy.control.CompartmentControlPolicy;

/**
 *
 */
public class CompartmentControlImpl
        implements Compartment, CacheableSecurityComponent {

    private final String portion;
    private final String banner;
    private final SubCompartment[] subcompartments;
    private final CompartmentControlPolicy policy;
    private final String cacheKey;

    private CompartmentControlImpl(String cacheKey, CompartmentControlPolicy policy,
            String portion, String banner, SubCompartment... subcompartments) {
        this.cacheKey = cacheKey;
        this.policy = policy;
        this.portion = portion;
        this.banner = banner;
        this.subcompartments = subcompartments;
    }

    public static CompartmentControlImpl instance(CompartmentControlPolicy policy,
            String portion, String banner, SubCompartment... subcompartments) {
        final String cacheKey = generateKey(policy, portion);
        CompartmentControlImpl cached = ComponentCache.get(
                CompartmentControlImpl.class, policy.getPolicyName(), cacheKey);
        if (cached == null) {
            cached = new CompartmentControlImpl(cacheKey, policy, portion,
                    banner, subcompartments);
            ComponentCache.put(cached);
        }
        return cached;
    }

    private static String generateKey(CompartmentControlPolicy policy, String portion) {
        return policy.getCategory() + ":" + portion;
    }

    @Override
    public SubCompartment[] getSubCompartments() {
        return subcompartments;
    }

    /**
     * Returns just the portion marking of the compartment, not including the
     * portion marking(s) of any subcomponents.
     *
     * @return
     */
    @Override
    public String getPortion() {
        return portion;
    }

    /**
     * Returns just the banner marking of the compartment, not including the
     * portion marking(s) of any subcomponents.
     *
     * @return
     */
    @Override
    public String getBanner() {
        return banner;
    }

    @Override
    public String cacheKey() {
        return cacheKey;
    }

    @Override
    public String getPolicyName() {
        return policy.getPolicyName();
    }

}
