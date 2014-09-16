package org.geoint.lbac.impl.marking;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import org.geoint.lbac.marking.Control;
import org.geoint.lbac.policy.SimpleCategoryPolicy;

/**
 * A SecurityCategory which directly contains {@link Control}s.
 *
 */
public class SimpleSecurityCategory extends AbstractSecurityCategory {

    private final SimpleCategoryPolicy policy;
    private final Control[] controls;
    private String cachedPortion;
    private String cachedBanner;

    public SimpleSecurityCategory(String name, SimpleCategoryPolicy policy,
            Control... controls) {
        super(name);
        this.policy = policy;
        this.controls = controls;
    }

    public Control[] getControls() {
        return controls;
    }

    @Override
    public SimpleCategoryPolicy getPolicy() {
        return policy;
    }

    @Override
    public String getPath() {
        return policy.getPath();
    }

    @Override
    public String getPortion() {
        if (cachedPortion == null) {
            cachedPortion = stringify(true);
        }
        return cachedPortion;
    }

    @Override
    public String getBanner() {
        if (cachedBanner == null) {
            cachedBanner = stringify(false);
        }
        return stringify(false);
    }

    /**
     * Uses the {@link CategoryFormat} to format the category.
     *
     * @param portion
     */
    private String stringify(boolean portion) {
        StringBuilder sb = new StringBuilder();

        //append optional category label
        appendLabel(sb, policy);

        //sort controls based on defined sort order for the type of marking needed
        Comparator<Control> comparator = null;
        if (portion) {
            comparator = policy.getFormat().getSortOrder().getPortion();
        } else {
            comparator = policy.getFormat().getSortOrder().getBanner();
        }
        SortedSet<Control> sortedControls = new TreeSet<>(comparator);
        sortedControls.addAll(Arrays.asList(controls));

        //append controls
        Iterator<Control> iterator = sortedControls.iterator();
        while (iterator.hasNext()) {
            Control c = iterator.next();
            if (portion) {
                sb.append(c.getPortion());
            } else {
                sb.append(c.getBanner());
            }
            if (iterator.hasNext()) {
                sb.append(policy.getFormat().getControlSeparator());
            }
        }

        return sb.toString();
    }

}
