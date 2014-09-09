package org.geoint.lbac.impl.policy;

import java.util.HashMap;
import java.util.Map;
import org.geoint.lbac.LbacException;
import org.geoint.lbac.impl.policy.restriction.SecurityRestriction;
import org.geoint.lbac.marking.InvalidSecurityMarkingException;
import org.geoint.lbac.marking.SecurityMarking;
import org.geoint.lbac.marking.SecurityMarkingBuilder;
import org.geoint.lbac.policy.CategoryPolicy;
import org.geoint.lbac.policy.NestedCategoryPolicy;
import org.geoint.lbac.policy.SecurityPolicy;
import org.geoint.lbac.policy.SimpleCategoryPolicy;

/**
 *
 */
public class SecurityPolicyImpl implements SecurityPolicy {

    private final String name;
    private final SecurityRestriction[] restrictions;
    private final Map<String, CategoryPolicy> categories;

    public SecurityPolicyImpl(String name, CategoryPolicy[] categories, 
            SecurityRestriction[] restrictions) {
        this.name = name;
        this.restrictions = restrictions;
        this.categories = new HashMap<>();
        for (CategoryPolicy p : categories) {
            if (p instanceof NestedCategoryPolicy) {
                final String root = p.getCategoryName();
                for (SimpleCategoryPolicy cp : ((NestedCategoryPolicy) p).getCategoryPolicies()) {
                    this.categories.put(root + "." + cp.getCategoryName(), cp);
                }
            }
            this.categories.put(p.getCategoryName(), p);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String[] getCategoryNames() {
        return (String[]) categories.keySet().toArray();
    }

    @Override
    public CategoryPolicy getCategory(String categoryName) {
        return categories.get(categoryName);
    }

    @Override
    public SecurityMarking valueOf(String marking) throws InvalidSecurityMarkingException {

    }

    @Override
    public SecurityMarking valueOf(SecurityMarking context, String marking) throws InvalidSecurityMarkingException {

    }

    @Override
    public SecurityMarking valueOf(byte[] marking) throws InvalidSecurityMarkingException {

    }

    @Override
    public SecurityMarking valueOf(SecurityMarking context, byte[] marking) throws InvalidSecurityMarkingException {

    }

    @Override
    public boolean isPermitted(SecurityMarking m1, SecurityMarking m2) throws InvalidSecurityMarkingException {

    }

    @Override
    public SecurityMarking merge(SecurityMarking... markings) throws LbacException {

    }

    @Override
    public SecurityMarking merge(String... markings) throws LbacException {

    }

    @Override
    public SecurityMarkingBuilder markingBuilder() {

    }

    /**
     * <b>IMPLEMENTATION METHOD</b>
     *
     * Returns the runtime restrictions applied to the policy.
     *
     * @return
     */
    public SecurityRestriction[] getRestrictions() {
        return restrictions;
    }
}
