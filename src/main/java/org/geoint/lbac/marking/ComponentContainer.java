package org.geoint.lbac.marking;

import org.geoint.lbac.policy.ContainerPolicy;

/**
 * Contains one or more {@link SecurityComponent}.
 * 
 * @param <C>
 */
public interface ComponentContainer<C extends SecurityComponent>
        extends SecurityComponent {

    /**
     * Return all the components contained within this continer.
     * 
     * @return 
     */
    C[] getComponents();
    
    /**
     * Returns the contains component by it's path name.
     * 
     * @param path
     * @return 
     */
    C getComponent(String path);
    
    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    ContainerPolicy getPolicy();

}
