package org.geoint.capco.marking;

import org.geoint.capco.marking.SecurityMarking;
import org.geoint.capco.marking.component.Country;

/**
 * For classified non-U.S. documents
 *
 */
public interface ForeignSecurityMarking extends SecurityMarking{

    Country getOriginatingCountry();

}
