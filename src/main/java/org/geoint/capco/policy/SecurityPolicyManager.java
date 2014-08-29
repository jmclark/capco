package org.geoint.capco.policy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.geoint.capco.spi.store.SecurityPolicyStore;

/**
 * Manages the available security policies.
 * <p>
 * The manager attempts to discover available {@link SecurityPolicyStore}
 * through the {@link ServiceLoader}. Upon discovery, the manager will attempt
 * to load any policies available to the store. This allows the store to provide
 * one or more policies at load time.
 */
public final class SecurityPolicyManager {

    private static final Map<String, SecurityPolicy> policies = new HashMap<>();
    private static final Map<String, Set<SecurityPolicyStore>> stores = new HashMap<>();
    private static final Logger logger = Logger.getLogger(SecurityPolicyManager.class.getName());
    public static final String PROPERTY_STORE_DIR = "capco.store.dir";
    public static final String PROPERTY_STORE_FILE = "capcp.store.file";
    
    static {
        //load policies from ServiceLoader
        load(new ServiceLoaderPolicyStore());
    }

    /**
     * Return the requested policy by policy name.
     *
     * @param policyName
     * @return the policy
     * @throws UnknownPolicyException if requested policy is not found
     */
    public static SecurityPolicy getPolicy(String policyName) throws UnknownPolicyException {
        SecurityPolicy policy = policies.get(policyName);
        if (policy == null) {
            throw new UnknownPolicyException(policyName);
        }
        return policy;
    }

    public static String[] getPolicies() {
        Set<String> policyNames = policies.keySet();
        return policyNames.toArray(new String[policyNames.size()]);
    }

    /**
     * Loads the policies from the provided store.
     * <p>
     * Policies are considered unique by their policy name. If more than one
     * policy is found by the same policy name, the last policy loaded by this
     * name wins. If "save" is called, every store that contains a policy by
     * that name will receive the single policy...synchronizing all stores.
     *
     * @param store
     */
    private static void load(SecurityPolicyStore store) {
        for (SecurityPolicy p : store.retrievePolicies()) {
            final String policyName = p.getName();

            //add the policy to the available policies
            policies.put(policyName, p);

            //add the store to the available policy stores
            if (!stores.containsKey(policyName)) {
                stores.put(policyName, new HashSet<SecurityPolicyStore>());
            }
            stores.get(policyName).add(store);
        }
    }


    /**
     * Loads policies from discoverable policy stores.
     */
    private static class ServiceLoaderPolicyStore implements SecurityPolicyStore {

        private final ServiceLoader<SecurityPolicyStore> loader;
        private final SecurityPolicy[] policies;

        {
            loader = ServiceLoader.load(SecurityPolicyStore.class);

            List<SecurityPolicy> policyBag = new ArrayList<>();
            for (SecurityPolicyStore ps : loader) {
                policyBag.addAll(Arrays.asList(ps.retrievePolicies()));
            }
            policies = policyBag.toArray(new SecurityPolicy[policyBag.size()]);
        }

        public SecurityPolicy[] retrievePolicies() {
            return policies;
        }
    }
}
