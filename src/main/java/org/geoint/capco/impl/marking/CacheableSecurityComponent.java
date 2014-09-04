package org.geoint.capco.impl.marking;

import org.geoint.capco.marking.SecurityComponent;

/**
 *
 */
public interface CacheableSecurityComponent extends SecurityComponent {

    /**
     * Returns the key used by the cache.
     *
     * @return
     */
    String cacheKey();

    /**
     * Returns the name of the {@link SecurityPolicy}.
     *
     * @return
     */
    String getPolicyName();

}
