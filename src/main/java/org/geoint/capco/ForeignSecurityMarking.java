package org.geoint.capco;

import org.geoint.capco.impl.policy.Country;

/**
 * For classified non-U.S. documents
 *
 */
public interface ForeignSecurityMarking extends SecurityMarking{

    Country getOriginatingCountry();

}
