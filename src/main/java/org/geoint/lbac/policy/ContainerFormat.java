package org.geoint.lbac.policy;

import org.geoint.lbac.marking.SecurityLabel;

/**
 *
 */
public interface ContainerFormat {

    public SecurityLabel getLabel();

    public SecurityLabel getLabelSeparator();

    public SecurityLabel getControlSeparator();

    public SortOrder getSortOrder();
}
