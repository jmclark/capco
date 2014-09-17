package org.geoint.lbac.policy;

import java.util.Comparator;
import org.geoint.lbac.marking.ComponentContainer;

/**
 *
 * @param <P>
 */
public interface ContainerPolicy<P extends ComponentPolicy>
        extends ComponentPolicy, Iterable<P> {

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
    Comparator<P> getPortionComparator();

    /**
     * Return the comparator to use to sort the components by banner marking.
     *
     * @return
     */
    Comparator<P> getBannerComparator();

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
    ComponentContainer getComponent();
}
