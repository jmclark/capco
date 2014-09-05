package org.geoint.lbac.impl;

import java.util.HashMap;
import java.util.Map;
import org.geoint.lbac.impl.marking.CacheableSecurityComponent;

/**
 * Centralized security component cache.
 */
public class ComponentCache {

    private final static Map<String, CacheableSecurityComponent> cache
            = new HashMap<>();
    private static final String KEY_SEPARATOR = ":";

    /**
     * Returns the cached component for the key.
     *
     * @param <T>
     * @param type
     * @param policyName
     * @param key
     * @return cached component or null if it isn't cached
     */
    public static <T extends CacheableSecurityComponent> T get(Class<T> type,
            String policyName, String key) {
        return (T) cache.get(generateKey(type, policyName, key));
    }

    /**
     * Cache a component.
     *
     * @param component
     */
    public static void put(CacheableSecurityComponent component) {
        final String key = generateKey(
                component.getClass(),
                component.getPolicyName(),
                component.cacheKey());
        cache.put(key, component);
    }

    private static <T extends CacheableSecurityComponent> String
            generateKey(Class<T> type, String policyName, String key) {
        return type.getName() + KEY_SEPARATOR + policyName + KEY_SEPARATOR + key;
    }
}
