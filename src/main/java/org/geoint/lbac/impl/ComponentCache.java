package org.geoint.lbac.impl;

import java.util.HashMap;
import java.util.Map;
import org.geoint.lbac.marking.SecurityComponent;

/**
 * Centralized security component cache.
 */
public class ComponentCache {

    private final static Map<String, SecurityComponent> cache
            = new HashMap<>();  //not weak...we want it to stick around

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
        return (T) cache.get(componentPath);
    }

    /**
     * Cache a component.
     *
     * @param component
     */
    public static void put(SecurityComponent component) {
        cache.put(component.getPath(), component);
    }

}
