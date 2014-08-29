package org.geoint.capco.spi.store;

import org.geoint.capco.SecurityPolicy;

/**
 *
 */
public interface SecurityPolicyStore {

    SecurityPolicy[] retrievePolicies();
}
