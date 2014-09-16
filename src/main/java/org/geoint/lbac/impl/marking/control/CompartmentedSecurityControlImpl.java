package org.geoint.lbac.impl.marking.control;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import org.geoint.lbac.marking.SecurityComponent;
import org.geoint.lbac.marking.control.Compartment;
import org.geoint.lbac.marking.control.CompartmentedSecurityControl;
import org.geoint.lbac.marking.control.Control;
import org.geoint.lbac.policy.control.CompartmentedControlFormat;
import org.geoint.lbac.policy.control.CompartmentedControlPolicy;

/**
 *
 */
public class CompartmentedSecurityControlImpl
        implements CompartmentedSecurityControl {

    private final String compartmentPortion;
    private final String compartmentBanner;
    private final CompartmentControlImpl[] compartments;
    private final CompartmentedControlPolicy policy;
    private String cachedPortion;
    private String cachedBanner;

    private CompartmentedSecurityControlImpl(
            CompartmentedControlPolicy policy,
            String compartmentPortion,
            String compartmentBanner, CompartmentControlImpl... compartments) {
        this.compartmentPortion = compartmentPortion;
        this.compartmentBanner = compartmentBanner;
        this.compartments = compartments;
        this.policy = policy;
    }

    public static CompartmentedSecurityControlImpl instance(
            CompartmentedControlPolicy policy,
            String compartmentPortion, String compartmentBanner,
            CompartmentControlImpl... compartments) {
        return new CompartmentedSecurityControlImpl(policy,
                compartmentPortion, compartmentBanner, compartments);
    }

    @Override
    public String getPath() {
        return policy.getPath();
    }

    @Override
    public Compartment[] getCompartments() {
        return compartments;
    }

    @Override
    public CompartmentedControlPolicy getPolicy() {
        return policy;
    }

    /**
     * Returns the entire portion marking, including multiple compartments and
     * their subcomponents.
     *
     * @return
     */
    @Override
    public String getPortion() {
        //purposely not synchronizing, overhead-to-benefit isn't worth it
        if (cachedPortion == null) {
            cachedPortion = stringify(true);
        }
        return cachedPortion;
    }

    /**
     * Returns the entire banner marking, including multiple compartments and
     * their subcomponents.
     *
     * @return
     */
    @Override
    public String getBanner() {
        if (cachedBanner == null) {
            cachedBanner = stringify(false);
        }
        return cachedBanner;
    }

    /**
     * Convert this component, its subcomponents, as a String.
     *
     * @param portion
     * @return
     */
    private String stringify(boolean portion) {
        StringBuilder sb = new StringBuilder();
        CompartmentedControlFormat format = policy.getFormat();
        Comparator<SecurityComponent> componentComparator;
        Comparator<SecurityComponent> subComparator;

        if (portion) {
            sb.append(compartmentPortion);
            componentComparator = format.getCompartmentSortOrder().getPortion();
            subComparator = format.getSubcompartmentSortOrder().getPortion();
        } else {
            sb.append(compartmentBanner);
            componentComparator = format.getCompartmentSortOrder().getBanner();
            subComparator = format.getSubcompartmentSortOrder().getBanner();
        }

        if (compartments.length == 0) {
            return sb.toString();
        }

        //sort the compartments
        SortedSet<CompartmentControlImpl> sortedCompartments
                = new TreeSet<>(componentComparator);
        sortedCompartments.addAll(Arrays.asList(compartments));

        Iterator<CompartmentControlImpl> compartmentIterator = sortedCompartments.iterator();
        while (compartmentIterator.hasNext()) {
            //append a comparment separator
            if (portion) {
                sb.append(format.getCompartmentSeparator().getPortion());
            } else {
                sb.append(format.getCompartmentSeparator().getBanner());
            }

            CompartmentControlImpl compartment = compartmentIterator.next();
            //append the compartment token
            if (portion) {
                sb.append(compartment.getPortion());
            } else {
                sb.append(compartment.getBanner());
            }

            if (compartment.getSubCompartments().length > 0) {

                //sort and append the subcompartments
                SortedSet<Control> sortedSubs = new TreeSet<>(subComparator);
                sortedSubs.addAll(Arrays.asList(compartment.getSubCompartments()));
                Iterator<Control> subIterator = sortedSubs.iterator();

                while (subIterator.hasNext()) {
                    //append the subcompartment separator
                    if (subIterator.hasNext()) {
                        if (portion) {
                            sb.append(format.getSubcomparmentSeparator().getPortion());
                        } else {
                            sb.append(format.getSubcomparmentSeparator().getBanner());
                        }
                    }

                    //append the subcompartment
                    Control sub = subIterator.next();
                    if (portion) {
                        sb.append(sub.getPortion());
                    } else {
                        sb.append(sub.getBanner());
                    }
                }
            }
        }

        return sb.toString();
    }

}
