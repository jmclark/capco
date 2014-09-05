package org.geoint.lbac.impl.marking;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import org.geoint.lbac.marking.SecurityCategory;
import org.geoint.lbac.policy.CategoryPolicy;
import org.geoint.lbac.policy.NestedCategoryPolicy;

/**
 *
 */
public class NestedSecurityCategory extends AbstractSecurityCategory {

    private final NestedCategoryPolicy policy;
    private final SimpleSecurityCategory[] categories;
    private String cachedPortion;
    private String cachedBanner;

    public NestedSecurityCategory(String name, NestedCategoryPolicy policy,
            SimpleSecurityCategory... categories) {
        super(name);
        this.policy = policy;
        this.categories = categories;
    }

    @Override
    public CategoryPolicy getPolicy() {
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
        return cachedBanner;
    }

    private String stringify(boolean portion) {
        StringBuilder sb = new StringBuilder();

        //append optional category label
        appendLabel(sb, policy);

        //sort components based on component comparator
        Comparator<SecurityCategory> catComparator;
        if (portion) {
            catComparator = policy.getFormat().getSortOrder().getPortion();
        } else {
            catComparator = policy.getFormat().getSortOrder().getBanner();
        }

        SortedSet<SimpleSecurityCategory> sortedCategories = new TreeSet<>(catComparator);
        sortedCategories.addAll(Arrays.asList(categories));

        //for each category, sort and append the controls
        Iterator<SimpleSecurityCategory> catIterator = sortedCategories.iterator();
        while (catIterator.hasNext()) {
            SimpleSecurityCategory cat = catIterator.next();
            if (portion) {
                sb.append(cat.getPortion());
            } else {
                sb.append(cat.getBanner());
            }

            if (catIterator.hasNext()) {
                sb.append(policy.getFormat().getControlSeparator());
            }
        }

        return sb.toString();
    }

}
