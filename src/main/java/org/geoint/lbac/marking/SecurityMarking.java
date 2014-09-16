package org.geoint.lbac.marking;

import org.geoint.lbac.policy.SecurityPolicy;

/**
 * A SecurityMarking provides a human-readable representation of the security 
 * classification of data, as well as an an interface to access the underlying 
 * construct of the complex security label.
 */
public interface SecurityMarking extends SecurityLabel {

    /**
     * Return the underlying policy for this marking.
     *
     * @return
     */
    SecurityPolicy getPolicy();

    /**
     * Return the marking as a byte array.
     *
     * @return
     */
    byte[] asBytes();

    /**
     * Determines if the provided marking is permitted under this label.
     *
     * @param marking
     * @return
     */
    boolean isPermitted(SecurityMarking marking);

    /**
     * Determines if the provided stringified marking is permitted under this
     * marking.
     *
     * @param marking
     * @return
     * @throws InvalidSecurityMarkingException if the provided string is not a
     * valid marking in this policy
     */
    boolean isPermitted(String marking) throws InvalidSecurityMarkingException;
    
    /**
     * Merges this marking and the provided marking(s).
     * 
     * @param markings
     * @return the merged marking
     * @throws InvalidSecurityMarkingException if at least one marking is not valid in the policy of this marking
     */
    SecurityMarking merge(SecurityMarking... markings) throws InvalidSecurityMarkingException;
    
    /**
     * Merges this marking and the provided stringified marking(s).
     * 
     * @param markings
     * @return the merged marking
     * @throws InvalidSecurityMarkingException if at least one marking is not a valid security marking in this policy
     */
    SecurityMarking merge(String... markings) throws InvalidSecurityMarkingException;
    
    /**
     * Return the category components of the marking.
     * 
     * @return 
     */
    SecurityComponent[] getComponents();
    
}
