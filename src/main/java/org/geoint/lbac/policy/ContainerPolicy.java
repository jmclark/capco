package org.geoint.lbac.policy;

import java.util.Comparator;
import org.geoint.lbac.marking.ComponentContainer;
import org.geoint.lbac.marking.SecurityComponent;

/**
 *
 * @param <P> the policy type of the container security components
 * @param <C> the type of the contained security components
 */
public interface ContainerPolicy<P extends ComponentPolicy, C extends SecurityComponent>
        extends ComponentPolicy<ComponentContainer>, Iterable<P> {

    String DEFAULT_PORTION_PREFIX = "";
    String DEFAULT_BANNER_PREFIX = "";
    String DEFAULT_SEPARATOR = "/";

    /**
     * Returns the name of the container.
     *
     * The name of the container is used for the path designation (since the
     * portion "prefix" is optional).
     *
     * The name can also be used to label the container on the GUI. The category
     * name is handled as case-insensitive and accepts all UTF-8 character
     * types, so the category name should be displayable.
     *
     * @return
     */
    String getContainerName();

    /**
     * Optional prefix rendered for the container when formatted as a portion
     * marking.
     *
     * @return
     */
    String getPortionPrefix();

    /**
     * Optional prefix rendered for the container when formatted as a banner
     * marking.
     *
     * @return
     */
    String getBannerPrefix();

    /**
     * The "glue" character(s) to use to join two or more components together.
     *
     * The default separator is a forward-slash "/".
     *
     * @return
     */
    String getComponentSeparator();

    /**
     * Returns a comparator to use to sort the components by portion marking.
     *
     * @return
     */
    Comparator<C> getPortionComparator();

    /**
     * Return the comparator to use to sort the components by banner marking.
     *
     * @return
     */
    Comparator<C> getBannerComparator();

    /**
     * Returns an instance of the component container.
     *
     * Container instances are NOT to be cached, as they will contain a variable
     * number of instances depending on a marking.
     *
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    ComponentContainer<P, C> getComponent();
}
