package org.geoint.capco.impl.policy;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class SciComponent extends MarkingComponent {

    private final List<String> subcomponents = new ArrayList<>();

    public List<String> getSubcomponents() {
        return subcomponents;
    }

    public void addSubcomponent(String sub) {
        this.subcomponents.add(sub);
    }
}
