package org.geoint.capco;

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
import org.geoint.capco.impl.policy.UnknownPolicyException;
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
    public static void load(SecurityPolicyStore store) {
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
     * Save the policy back to its original store(s).
     *
     * @param policy
     */
    public static void save(SecurityPolicy policy) {
        final String policyName = policy.getName();

        for (SecurityPolicyStore ps : stores.get(policy.getName())) {
            try {
                ps.save(policy);
            } catch (IOException ex) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to save security policy '").append(policyName);
                sb.append("' to policy store '").append(ps.getClass().getName());
                logger.log(Level.SEVERE, sb.toString(), ex);
            }
        }
    }

    /**
     * Save all policies.
     */
    public static void saveAll() {
        for (Entry<String, SecurityPolicy> e : policies.entrySet()) {
            save(e.getValue());
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

        @Override
        public SecurityPolicy[] retrievePolicies() {
            return policies;
        }

        /**
         * Save the policy to all stores that contain that policy name.
         * <p>
         * This is done intentionally to keep multiple policy stores in sync.
         *
         * @param policy
         * @throws IOException
         */
        @Override
        public void save(SecurityPolicy policy) throws IOException {
            for (SecurityPolicyStore ps : loader) {
                for (SecurityPolicy p : ps.retrievePolicies()) {
                    if (p.getName().contentEquals(policy.getName())) {
                        ps.save(policy);
                    }
                }
            }
        }
    }
}
