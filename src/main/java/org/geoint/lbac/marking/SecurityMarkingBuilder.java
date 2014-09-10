package org.geoint.lbac.marking;

import org.geoint.lbac.policy.SecurityPolicy;
import org.geoint.lbac.impl.policy.restriction.SecurityRestrictionException;

/**
 * API to create, or modify, a {@link SecurityMarking}.
 *
 */
public interface SecurityMarkingBuilder {

    /**
     * Adds a {@link SecurityComponent} to a category within the
     * {@link SecurityMarking}, first checking policy restrictions.
     *
     * This method if functionally equivalent to retrieving the control instance
     * from the {@link SecurityPolicy} and calling 
     * {@link SecurityMarkingBuilder#addControl(SecurityComponent) }
     *
     * @param componentPath
     * @return
     * @throws UnknownSecurityComponentException if the control is unknown in
     * the context of this policy
     * @throws SecurityRestrictionException if a restriction prevents this
     * action from happening
     */
    SecurityMarkingBuilder addComponent(String componentPath)
            throws UnknownSecurityComponentException, SecurityRestrictionException;

    /**
     * Attempts to add a {@link SecurityComponent} to the {@link SecurityMarking}.
     *
     * The {@link SecurityPolicy} is first checked for any restrictions that may
     * morph the action taken by adding this control (based on those
     * restrictions).
     *
     * @param component
     * @return
     * @throws UnknownSecurityComponentException if the control provided is not
     * of the same policy, and the policy in this context does not contain that
     * control
     * @throws SecurityRestrictionException if a restriction prevents this
     * action from happening
     */
    SecurityMarkingBuilder addComponent(SecurityComponent component)
            throws UnknownSecurityComponentException, SecurityRestrictionException;

    /**
     * Removes a {@link SecurityComponent} to a category within the
     * {@link SecurityMarking}, first checking policy restrictions.
     *
     * This method if functionally equivalent to retrieving the control instance
     * from the {@link SecurityPolicy} and calling 
     * {@link SecurityMarkingBuilder#removeControl(SecurityComponent) }
     *
     * @param componentPath
     * @return
     * @throws UnknownSecurityComponentException if the control is unknown in
     * the context of this policy
     * @throws SecurityRestrictionException if a restriction prevents this
     * action from happening
     */
    SecurityMarkingBuilder removeComponent(String componentPath)
            throws UnknownSecurityComponentException, SecurityRestrictionException;

    /**
     * Attempts to remove a {@link SecurityComponent} from the
     * {@link SecurityMarking}.
     *
     * The {@link SecurityPolicy} is first checked for any restrictions that may
     * morph the action taken by adding this control (based on those
     * restrictions).
     *
     * @param component
     * @return
     * @throws UnknownSecurityComponentException if the control provided is not
     * of the same policy, and the policy in this context does not contain that
     * control
     * @throws SecurityRestrictionException if a restriction prevents this
     * action from happening
     */
    SecurityMarkingBuilder removeComponent(SecurityComponent component)
            throws UnknownSecurityComponentException, SecurityRestrictionException;

}
