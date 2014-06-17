package org.geoint.capco.impl.policy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class SciComponent extends MarkingComponent {

    private final List<String> subcomponents = new ArrayList<>();

    public SciComponent(String portion, String banner, String... subcomponents) {
        super(portion, banner);
        this.subcomponents.addAll(Arrays.asList(subcomponents));
    }

    public List<String> getSubcomponents() {
        return subcomponents;
    }
}
