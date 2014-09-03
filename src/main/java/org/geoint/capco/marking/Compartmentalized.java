package org.geoint.capco.marking;

/**
 * Indicates that a security category is compartmentalized.
 *
 * A compartmentalized category is one that may contain one or more security
 * compartments, which may be further compartmentalized. Access to
 * compartmentalized data is normally restricted to users with the need-to-know.
 *
 * @param <C>
 */
public interface Compartmentalized<C extends Compartment> extends SecurityCategory {

    C[] getCompartments();
}
