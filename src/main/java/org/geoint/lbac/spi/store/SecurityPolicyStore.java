package org.geoint.lbac.spi.store;

import org.geoint.lbac.policy.SecurityPolicy;

/**
 *
 */
public interface SecurityPolicyStore {

    SecurityPolicy[] retrievePolicies();
}
