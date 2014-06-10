package org.geoint.capco;

/**
 * For classified non-U.S. documents
 *
 */
public interface ForeignSecurityMarking extends SecurityMarking{

    String getOriginatingCountry();

}
