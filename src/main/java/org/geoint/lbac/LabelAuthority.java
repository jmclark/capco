package org.geoint.lbac;

/**
 * Classification authority details.
 */
public interface LabelAuthority {

    String getClassifiedBy();

    String getDerivedBy();

    String getDeclassifyOn();

    String getDowngradeTo();

}
