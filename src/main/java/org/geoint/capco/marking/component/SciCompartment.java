package org.geoint.capco.marking.component;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * SCI compartment, which may contain zero or more subcompartments.
 */
public class SciCompartment extends MarkingComponent {

    private final Set<String> subcompartments;

    public SciCompartment(String portion, String banner, String... subcompartments) {
        super(portion, banner);
        this.subcompartments = new TreeSet<>();
        this.subcompartments.addAll(Arrays.asList(subcompartments));
    }

    public String[] getSubcompartments() {
        return (String[]) subcompartments.toArray();
    }

}
