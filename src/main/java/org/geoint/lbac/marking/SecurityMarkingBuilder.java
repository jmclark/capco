package org.geoint.lbac.marking;

import org.geoint.lbac.marking.control.SecurityControl;
import org.geoint.lbac.policy.SecurityPolicy;
import org.geoint.lbac.impl.policy.restriction.SecurityRestrictionException;

/**
 * API to create, or modify, a {@link SecurityMarking}.
 *
 */
public interface SecurityMarkingBuilder {

    /**
     * Adds a {@link SecurityControl} to a category within the
     * {@link SecurityMarking}, first checking policy restrictions.
     *
     * This method if functionally equivalent to retrieving the control instance
     * from the {@link SecurityPolicy} and calling 
     * {@link SecurityMarkingBuilder#addControl(SecurityControl) }
     *
     * @param category
     * @param control
     * @return
     * @throws UnknownSecurityComponentException if the control is unknown in
     * the context of this policy
     * @throws SecurityRestrictionException if a restriction prevents this
     * action from happening
     */
    SecurityMarkingBuilder addControl(String category, String control)
            throws UnknownSecurityComponentException, SecurityRestrictionException;

    /**
     * Attempts to add a {@link SecurityControl} to the {@link SecurityMarking}.
     *
     * The {@link SecurityPolicy} is first checked for any restrictions that may
     * morph the action taken by adding this control (based on those
     * restrictions).
     *
     * @param control
     * @return
     * @throws SecurityRestrictionException if a restriction prevents this
     * action from happening
     */
    SecurityMarkingBuilder addControl(SecurityControl control)
            throws SecurityRestrictionException;

    /**
     * Removes a {@link SecurityControl} to a category within the
     * {@link SecurityMarking}, first checking policy restrictions.
     *
     * This method if functionally equivalent to retrieving the control instance
     * from the {@link SecurityPolicy} and calling 
     * {@link SecurityMarkingBuilder#removeControl(SecurityControl) }
     * 
     * @param category
     * @param control
     * @return
     * @throws UnknownSecurityComponentException if the control is unknown in
     * the context of this policy
     * @throws SecurityRestrictionException if a restriction prevents this
     * action from happening 
     */
    SecurityMarkingBuilder removeControl(String category, String control)
            throws UnknownSecurityComponentException, SecurityRestrictionException;

    /**
     * Attempts to remove a {@link SecurityControl} from the 
     * {@link SecurityMarking}.
     * 
     * The {@link SecurityPolicy} is first checked for any restrictions that may
     * morph the action taken by adding this control (based on those
     * restrictions).
     * 
     * @param control
     * @return
     * @throws SecurityRestrictionException if a restriction prevents this
     * action from happening 
     */
    SecurityMarkingBuilder removeControl(SecurityControl control)
            throws SecurityRestrictionException;

}
