package org.geoint.lbac.impl;

import java.util.HashMap;
import java.util.Map;
import org.geoint.lbac.marking.Control;
import org.geoint.lbac.marking.SecurityComponent;
import org.geoint.lbac.marking.SecurityMarking;

/**
 * On-heap caching for cachable label objects.
 */
public class LabelCache {

    private final static Map<String, SecurityComponent> controls
            = new HashMap<>();  //not weak...we want it to stick around
    private final static Map<String, SecurityMarking> portions = new HashMap<>();
    private final static Map<String, SecurityMarking> banners = new HashMap<>();

    /**
     * Retrieve a {@link SecurityMarking} from the cache.
     * 
     * 
     * @param marking
     * @return 
     */
    public static SecurityMarking get(String marking) {
        return (portions.containsKey(marking))
                ? portions.get(marking)
                : banners.get(marking);
    }

    /**
     * Cache a {@link SecurityMarking}.
     * 
     * @param marking 
     */
    public static void put(SecurityMarking marking) {
        if (portions.containsKey(marking.getPortion())) {
            portions.put(marking.getPortion(), marking);
            banners.put(marking.getBanner(), marking);
        }
    }

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
        return (T) controls.get(componentPath);
    }

    /**
     * Cache a component.
     *
     * @param control
     */
    public static void put(Control control) {
        controls.put(control.getPath(), control);
    }

}
