package org.geoint.lbac.policy;

import org.geoint.lbac.marking.ComponentContainer;
import org.geoint.lbac.marking.SecurityMarking;

/**
 *
 */
public interface ContainerPolicy extends ComponentPolicy {

    /**
     * Returns the name of the category.
     *
     * @return
     */
    String getCategoryName();

    /**
     * Returns the optional container format to apply to the
     * {@link SecurityMarking} while rendering
     *
     * @return
     */
    ContainerFormat getFormat();

    /**
     * Return the policies for the components that are permitted within this 
     * container.
     * 
     * @return 
     */
    ComponentPolicy[] getChildren();
    
    /**
     * Returns an instance of the component container.
     * 
     * {@inheritDoc}
     * 
     * @return 
     */
    @Override
    ComponentContainer getComponent();
}
