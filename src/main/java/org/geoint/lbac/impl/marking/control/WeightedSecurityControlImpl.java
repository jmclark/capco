package org.geoint.lbac.impl.marking.control;

import java.util.Objects;
import org.geoint.lbac.impl.ComponentCache;
import static org.geoint.lbac.impl.marking.control.StandardSecurityControlImpl.generateKey;
import org.geoint.lbac.marking.control.WeightedSecurityControl;
import org.geoint.lbac.policy.control.WeightedControlPolicy;

/**
 *
 */
public class WeightedSecurityControlImpl extends StandardSecurityControlImpl
        implements WeightedSecurityControl {

    private final int weight;

    private WeightedSecurityControlImpl(String cachKey, WeightedControlPolicy policy,
            String portion, String banner,
            int weight) {
        super(cachKey, policy, portion, banner);
        this.weight = weight;
    }

    public static WeightedSecurityControlImpl instance(
            WeightedControlPolicy policy,
            String portion, String banner, int weight) {
        final String cacheKey = generateKey(policy, portion);
        WeightedSecurityControlImpl cached = ComponentCache.get(
                WeightedSecurityControlImpl.class, policy.getPolicyName(),
                cacheKey);
        if (cached == null) {
            cached = new WeightedSecurityControlImpl(cacheKey, policy,
                    portion, banner, weight);
            ComponentCache.put(cached);
        }
        return cached;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.weight;
        hash = 47 * hash + Objects.hashCode(this.getPortion());
        hash = 47 * hash + Objects.hashCode(this.getBanner());
        hash = 47 * hash + Objects.hashCode(this.getPolicy());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WeightedSecurityControlImpl other = (WeightedSecurityControlImpl) obj;
        if (this.weight != other.weight) {
            return false;
        }
        if (!Objects.equals(this.getPortion(), other.getPortion())) {
            return false;
        }
        if (!Objects.equals(this.getBanner(), other.getBanner())) {
            return false;
        }
        if (!Objects.equals(this.getPolicy(), other.getPolicy())) {
            return false;
        }
        return true;
    }

}
