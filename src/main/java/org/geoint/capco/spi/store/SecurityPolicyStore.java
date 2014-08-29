package org.geoint.capco.spi.store;

import org.geoint.capco.policy.SecurityPolicy;

/**
 *
 */
public interface SecurityPolicyStore {

    SecurityPolicy[] retrievePolicies();
}
