package org.geoint.lbac.impl.marking.control;

import org.geoint.lbac.marking.control.Compartment;
import org.geoint.lbac.marking.Control;
import org.geoint.lbac.policy.ComponentPolicy;
import org.geoint.lbac.policy.control.CompartmentControlPolicy;

/**
 *
 */
public class CompartmentControlImpl
        implements Compartment {

    private final String portion;
    private final String banner;
    private final Control[] subcompartments;
    private final CompartmentControlPolicy policy;

    private CompartmentControlImpl(CompartmentControlPolicy policy,
            String portion, String banner, Control... subcompartments) {
        this.policy = policy;
        this.portion = portion;
        this.banner = banner;
        this.subcompartments = subcompartments;
    }

    public static CompartmentControlImpl instance(CompartmentControlPolicy policy,
            String portion, String banner, Control... subcompartments) {
        return new CompartmentControlImpl(policy, portion,
                banner, subcompartments);
    }

    @Override
    public CompartmentControlPolicy getPolicy() {
        return policy;
    }

    @Override
    public String getPath() {
        return policy.getPath();
    }

    @Override
    public Control[] getSubCompartments() {
        return subcompartments;
    }

    /**
     * Returns just the portion marking of the compartment, not including the
     * portion marking(s) of any subcomponents.
     *
     * @return
     */
    @Override
    public String getPortion() {
        return portion;
    }

    /**
     * Returns just the banner marking of the compartment, not including the
     * portion marking(s) of any subcomponents.
     *
     * @return
     */
    @Override
    public String getBanner() {
        return banner;
    }

}
