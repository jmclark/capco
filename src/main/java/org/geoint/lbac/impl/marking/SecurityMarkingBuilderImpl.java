package org.geoint.lbac.impl.marking;

import org.geoint.lbac.impl.command.AddComponentCommand;
import org.geoint.lbac.impl.command.CommandExecutionException;
import org.geoint.lbac.impl.command.MarkingChangeCommand;
import org.geoint.lbac.impl.command.RemoveComponentCommand;
import org.geoint.lbac.impl.policy.SecurityPolicyImpl;
import org.geoint.lbac.impl.policy.restriction.SecurityRestriction;
import org.geoint.lbac.impl.policy.restriction.SecurityRestrictionException;
import org.geoint.lbac.marking.SecurityComponent;
import org.geoint.lbac.marking.SecurityMarking;
import org.geoint.lbac.marking.SecurityMarkingBuilder;
import org.geoint.lbac.marking.UnknownSecurityComponentException;
import org.geoint.lbac.marking.control.SecurityControl;
import org.geoint.lbac.policy.CategoryPolicy;
import org.geoint.lbac.policy.NestedCategoryPolicy;
import org.geoint.lbac.policy.SecurityComponentPolicy;
import org.geoint.lbac.policy.SimpleCategoryPolicy;
import org.geoint.lbac.policy.control.SecurityControlPolicy;

/**
 *
 */
public class SecurityMarkingBuilderImpl implements SecurityMarkingBuilder {

    private SecurityMarking currentMarking;
    private final SecurityPolicyImpl policy;

    public SecurityMarkingBuilderImpl(SecurityPolicyImpl policy) {
        this.policy = policy;
    }

    @Override
    public SecurityMarkingBuilder addComponent(String componentPath)
            throws UnknownSecurityComponentException, SecurityRestrictionException {
        AddComponentCommand cmd = new AddComponentCommand(currentMarking,
                getComponent(componentPath));
        return apply(cmd);
    }

    @Override
    public SecurityMarkingBuilder addComponent(SecurityComponent ctl)
            throws UnknownSecurityComponentException, SecurityRestrictionException {
        if (!ctl.getPolicy().equals(policy)) {
            ctl = getControlPolicy(ctl.getPolicy().getComponent())
            , ctl.getPortion()
            ).getControl();
        }
        return apply(new AddComponentCommand(currentMarking, ctl));
    }

    @Override
    public SecurityMarkingBuilder removeComponent(String category, String control)
            throws UnknownSecurityComponentException, SecurityRestrictionException {
        RemoveComponentCommand cmd = new RemoveComponentCommand(currentMarking,
                getControlPolicy(category, control).getControl());
        return apply(cmd);
    }

    @Override
    public SecurityMarkingBuilder removeComponent(SecurityControl ctl)
            throws UnknownSecurityComponentException, SecurityRestrictionException {
        if (!ctl.getPolicy().equals(policy)) {
            ctl = getControlPolicy(ctl.getPolicy().getCategory(), ctl.getPortion()).getControl();
        }
        return apply(new RemoveComponentCommand(currentMarking, ctl));
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
    private MarkingChangeCommand[] applyRestrictions(MarkingChangeCommand cmd)
            throws SecurityRestrictionException {

        for (SecurityRestriction r : policy.getRestrictions()) {
            MarkingChangeCommand[] changed = r.restrict(cmd);
            if (changed != null) {
                return changed;
            }
        }
        return null;
    }

    /**
     * Returns the SecurityComponent or throws and
     * UnknownSecurityComponentException if the control isn't found.
     *
     * @param category
     * @param control
     * @return
     * @throws UnknownSecurityComponentException
     */
    private SecurityComponent getComponent(String componentPath)
            throws UnknownSecurityComponentException {

        final String[] pathParts = componentPath.split(SecurityComponent.PATH_SEPARATOR);

        //ensure the policy for this component is valid
        if (!pathParts[0].contentEquals(policy.getName())) {
            throw new UnknownSecurityComponentException(policy,
                    componentPath, componentPath, "Invalid policy.");
        }

        SecurityComponentPolicy componentPolicy = 
                policy.getComponentPolicy(componentPath);
        
        return componentPolicy.getComponent();
    }

    /**
     * Applies restrictions, executing the resultant command after restrictions
     * are applied. If the command execution fails, attempts to roll back
     * changes applied (as applicable).
     *
     * note to those behind me: this method (potentially) is recursive.
     *
     * @param cmd
     * @throws CommandExecutionException thrown if executing a command has an
     * irrecoverable exception
     */
    private SecurityMarkingBuilderImpl apply(MarkingChangeCommand cmd)
            throws SecurityRestrictionException {

        final SecurityMarking previousMarking = currentMarking; //to be reapplied if there's an error
        try {
            //apply restrictions, could return new command(s), null, or throw exception
            MarkingChangeCommand[] rCmd = applyRestrictions(cmd);

            if (rCmd == null) {
                //passed restrictions without need to change the command
                cmd.apply(this);
            } else {
                //triggered a restriction which returned new command(s)
                //recursively call this method
                for (MarkingChangeCommand c : rCmd) {
                    apply(c);
                }
            }
        } catch (CommandExecutionException ex) {
            //set it back to how it was
            currentMarking = previousMarking;
        } catch (SecurityRestrictionException ex) {
            currentMarking = previousMarking;
            throw ex;
        }
        return this;
    }
}
