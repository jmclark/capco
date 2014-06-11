package org.geoint.capco.spi.store;

import java.io.IOException;
import org.geoint.capco.SecurityPolicy;

/**
 * SPI interface for retrieving and persisting SecurityPolicies.
 * 
 * Store instances must be registered with the ServiceLoader
 */
public interface SecurityPolicyStore {

    SecurityPolicy[] retrievePolicies();

    void save(SecurityPolicy policy) throws IOException;
}
