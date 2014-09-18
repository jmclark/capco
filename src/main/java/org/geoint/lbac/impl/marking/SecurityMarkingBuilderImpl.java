package org.geoint.lbac.impl.marking;

import org.geoint.lbac.impl.command.AddControlCommand;
import org.geoint.lbac.impl.command.CommandExecutionException;
import org.geoint.lbac.impl.command.MarkingChangeCommand;
import org.geoint.lbac.impl.command.RemoveControlCommand;
import org.geoint.lbac.impl.policy.SecurityPolicyImpl;
import org.geoint.lbac.impl.policy.restriction.SecurityRestriction;
import org.geoint.lbac.impl.policy.restriction.SecurityRestrictionException;
import org.geoint.lbac.marking.SecurityComponent;
import org.geoint.lbac.marking.SecurityMarking;
import org.geoint.lbac.marking.SecurityMarkingBuilder;
import org.geoint.lbac.marking.UnknownSecurityComponentException;
import org.geoint.lbac.marking.Control;
import org.geoint.lbac.policy.ComponentPolicy;

/**
 *
 */
public class SecurityMarkingBuilderImpl implements SecurityMarkingBuilder {

    private SecurityMarkingImpl currentMarking;
//    private Map<String, Control> currentControls;
    private final SecurityPolicyImpl policy;

    public SecurityMarkingBuilderImpl(SecurityPolicyImpl policy) {
        this.policy = policy;
    }

    @Override
    public SecurityMarkingBuilder addControl(String componentPath)
            throws UnknownSecurityComponentException, SecurityRestrictionException {
        SecurityComponent cmp = getComponent(componentPath);
        if (!(cmp instanceof Control)) {
            return this;
        }
        AddControlCommand cmd = new AddControlCommand(currentMarking,
                (Control) cmp);
        return apply(cmd);
    }

    @Override
    public SecurityMarkingBuilder addControl(Control ctl)
            throws UnknownSecurityComponentException, SecurityRestrictionException {

        return apply(new AddControlCommand(currentMarking, normalize(ctl)));
    }

    @Override
    public SecurityMarkingBuilder removeControl(String componentPath)
            throws UnknownSecurityComponentException, SecurityRestrictionException {
        SecurityComponent cmp = getComponent(componentPath);
        if (!(cmp instanceof Control)) {
            return this;
        }
        RemoveControlCommand cmd = new RemoveControlCommand(currentMarking,
                (Control) cmp);
        return apply(cmd);
    }

    @Override
    public SecurityMarkingBuilder removeControl(Control ctl)
            throws UnknownSecurityComponentException, SecurityRestrictionException {

        return apply(new RemoveControlCommand(currentMarking, normalize(ctl)));
    }

    private <C extends SecurityComponent> C normalize(C ctl)
            throws UnknownSecurityComponentException {

        if (!ctl.getPolicy().equals(policy)) {
            SecurityComponent cmp = getComponent(ctl.getPath());
            if (cmp == null) {
                throw new UnknownSecurityComponentException(policy,
                        ctl.getPath(), ctl.getPortion(), "Invalid policy and "
                        + "cannot component to policy.");
            }
        }
        return ctl;
    }

    /**
     * <b>IMPLEMENTATION METHOD</b>
     *
     * This method adds a {@link SecurityComponent} without running restriction
     * checks.
     *
     * @param control
     */
    public void doAdd(Control control) {
//        currentControls.put(control.getPath(), control);
//        currentMarking = SecurityMarkingImpl.generate(policy, currentControls);
        currentMarking = currentMarking.mergeWithoutChecks(control);
    }

    /**
     * <b>IMPLEMENTATION METHOD</b>
     *
     * This method removes a {@link SecurityComponent} without running
     * restriction checks.
     *
     * @param control
     */
    public void doRemove(Control control) {
        currentControls.remove(control.getPath());
        currentMarking = SecurityMarkingImpl.generate(policy, currentControls);
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
            //attempt to see if there is such a control in this policy
            String[] oldPath = componentPath.split(SecurityComponent.PATH_SEPARATOR);
            oldPath[0] = policy.getName();
            componentPath = StringUtils.join(oldPath, SecurityComponent.PATH_SEPARATOR);
        }

        ComponentPolicy componentPolicy
                = policy.getComponentPolicy(componentPath);

        if (componentPolicy == null) {
            throw new UnknownSecurityComponentException(policy,
                    componentPath, componentPath, "Invalid policy.");
        }

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
