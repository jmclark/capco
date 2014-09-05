package org.geoint.lbac.impl.marking;

import org.geoint.lbac.marking.SecurityCategory;
import org.geoint.lbac.policy.CategoryPolicy;

/**
 *
 */
public abstract class AbstractSecurityCategory implements SecurityCategory {

    private final String name;

    public AbstractSecurityCategory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getPortion();
    }

    protected void appendLabel(StringBuilder sb, CategoryPolicy policy) {
        //append label if there is one
        sb.append(policy.getFormat().getLabel().getPortion())
                .append(policy.getFormat().getLabelSeparator());
    }
}
