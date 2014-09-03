package org.geoint.capco.impl.marking;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import org.geoint.capco.marking.Compartmentalized;
import org.geoint.capco.marking.control.SecurityControl;
import org.geoint.capco.policy.CategoryFormat;
import org.geoint.capco.policy.SimpleCategoryPolicy;

/**
 * A SecurityCategory which directly contains {@link SecurityControl}s.
 *
 */
public class SimpleSecurityCategory extends AbstractSecurityCategory {

    private final SimpleCategoryPolicy policy;
    private final SecurityControl[] controls;
    private String cachedPortion;
    private String cachedBanner;

    public SimpleSecurityCategory(String name, SimpleCategoryPolicy policy,
            SecurityControl... controls) {
        super(name);
        this.policy = policy;
        this.controls = controls;
    }

    public SecurityControl[] getControls() {
        return controls;
    }

    @Override
    public SimpleCategoryPolicy getPolicy() {
        return policy;
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
        Comparator<SecurityControl> comparator = null;
        if (portion) {
            comparator = policy.getFormat().getSortOrder().getPortion();
        } else {
            comparator = policy.getFormat().getSortOrder().getBanner();
        }
        SortedSet<SecurityControl> sortedControls = new TreeSet<>(comparator);
        sortedControls.addAll(Arrays.asList(controls));

        //append controls
        Iterator<SecurityControl> iterator = sortedControls.iterator();
        while (iterator.hasNext()) {
            SecurityControl c = iterator.next();

            if (Compartmentalized.class.isAssignableFrom(c.getClass())) {
                //control is compartmentalized, we need to sort the 
                //sub-compartments as necessary
                
            } else {
                //control isn't compartmentalized
                if (portion) {
                    sb.append(c.getPortion());
                } else {
                    sb.append(c.getBanner());
                }
                if (iterator.hasNext()) {
                    sb.append(policy.getFormat().getSeparator());
                }
            }
        }

        return sb.toString();
    }

}
