package org.geoint.capco;

/**
 * Classification authority details.
 */
public interface ClassificationAuthority {

    String getClassifiedBy();

    String getDerivedBy();

    String getDeclassifyOn();

    String getDowngradeTo();

}
