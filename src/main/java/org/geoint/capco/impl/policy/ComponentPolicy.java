package org.geoint.capco.impl.policy;

import org.geoint.capco.CapcoException;
import org.geoint.capco.InvalidSecurityMarkingException;
import org.geoint.capco.marking.MarkingComponent;
import org.geoint.capco.marking.SecurityMarking;

/**
 * Container for a components policy settings.
 *
 * @param <C>
 */
public interface ComponentPolicy<C extends MarkingComponent> {

    void addComponent(C component) throws CapcoException;

    void addRestriction(ComponentRestriction res);

    /**
     * Validates this components values of the provided SecurityMarking.
     *
     * Default implementation checks the marking against all restrictions.
     *
     * @param marking
     * @throws InvalidSecurityMarkingException
     */
    void validate(SecurityMarking marking) throws InvalidSecurityMarkingException;

    /**
     * Determines if the provides string is a stringified representation of the
     * component.
     *
     * @param component
     * @return
     */
    boolean isComponentString(String component);

    /**
     * Returns the controls that are available, given the provided security
     * marking.
     *
     * @param marking if null, returns all controls
     * @return
     */
    C[] getAvailable(SecurityMarking marking);

}
