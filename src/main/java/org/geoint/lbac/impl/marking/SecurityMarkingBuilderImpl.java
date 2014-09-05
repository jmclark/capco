package org.geoint.lbac.impl.marking;

import org.geoint.lbac.impl.command.CompositeMarkingChangeCommand;
import org.geoint.lbac.impl.command.MarkingChangeCommand;
import org.geoint.lbac.impl.policy.SecurityPolicyImpl;
import org.geoint.lbac.impl.policy.restriction.SecurityRestriction;
import org.geoint.lbac.impl.policy.restriction.SecurityRestrictionException;
import org.geoint.lbac.marking.SecurityComponent;
import org.geoint.lbac.marking.SecurityMarkingBuilder;
import org.geoint.lbac.marking.UnknownSecurityComponentException;
import org.geoint.lbac.marking.control.SecurityControl;
import org.geoint.lbac.policy.CategoryPolicy;
import org.geoint.lbac.policy.NestedCategoryPolicy;
import org.geoint.lbac.policy.SimpleCategoryPolicy;
import org.geoint.lbac.policy.control.SecurityControlPolicy;

/**
 *
 */
public class SecurityMarkingBuilderImpl implements SecurityMarkingBuilder {

    private final SecurityPolicyImpl policy;

    public SecurityMarkingBuilderImpl(SecurityPolicyImpl policy) {
        this.policy = policy;
    }

    @Override
    public SecurityMarkingBuilder addControl(String category, String control)
            throws UnknownSecurityComponentException, SecurityRestrictionException {
        
    }

    /**
     * Returns the SecurityControlPolicy or null if not a valid control for the
     * requested category.
     *
     * @param category
     * @param control
     * @return
     * @throws UnknownSecurityComponentException
     */
    private SecurityControlPolicy getControlPolicy(String category, String control)
            throws UnknownSecurityComponentException {

        CategoryPolicy cat = policy.getCategory(category);
        if (cat == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown marking category '")
                    .append(category)
                    .append("'.  Unable to add control '")
                    .append(control)
                    .append("'.");
            throw new UnknownSecurityComponentException(policy, category,
                    control, sb.toString());
        }

        if (cat instanceof NestedCategoryPolicy) {
            StringBuilder sb = new StringBuilder();
            sb.append("Category '")
                    .append(category)
                    .append("' does not directly contain controls.");
            throw new UnknownSecurityComponentException(policy, category,
                    control, sb.toString());
        }

        return ((SimpleCategoryPolicy) cat).getControl(control);
    }

    @Override
    public SecurityMarkingBuilder addControl(SecurityControl control) throws SecurityRestrictionException {

    }

    @Override
    public SecurityMarkingBuilder removeControl(String category, String control) throws UnknownSecurityComponentException, SecurityRestrictionException {

    }

    @Override
    public SecurityMarkingBuilder removeControl(SecurityControl control) throws SecurityRestrictionException {

    }

    /**
     * <b>IMPLEMENTATION METHOD</b>
     *
     * This method adds a {@link SecurityComponent} without running restriction
     * checks.
     *
     * @param component
     */
    public void doAdd(SecurityComponent component) {

    }

    /**
     * <b>IMPLEMENTATION METHOD</b>
     *
     * This method removes a {@link SecurityComponent} without running
     * restriction checks.
     *
     * @param component
     */
    public void doRemove(SecurityComponent component) {

    }

    /**
     * Iterates through the {@link SecurityRestriction} instances within the
     * policy and checks for any changes to the command required by the
     * restrictions.
     *
     * If a restriction changes the command, the new command is returned. If no
     * changes are required, nothing (null) is returned.
     *
     * @param cmd
     * @return changed MarkingChangeCommand to use or null (no change necessary)
     * @throws SecurityRestrictionException
     */
    private MarkingChangeCommand applyRestrictions(MarkingChangeCommand cmd)
            throws SecurityRestrictionException {
        if (cmd instanceof CompositeMarkingChangeCommand) {
            throw new SecurityRestrictionException("Cannot apply restrictions to "
                    + "a composite restriction");
        }

        for (SecurityRestriction r : policy.getRestrictions()) {
            MarkingChangeCommand changed = r.restrict(cmd);
            if (changed != null) {
                return changed;
            }
        }
        return null;
    }

}
