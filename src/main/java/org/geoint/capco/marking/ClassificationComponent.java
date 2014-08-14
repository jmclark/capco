package org.geoint.capco.marking;

/**
 * Base classification component
 */
public class ClassificationComponent extends MarkingComponent {

    private final short weight;

    public ClassificationComponent(String portion, String banner, short weight) {
        super(portion, banner);
        this.weight = weight;
    }

    public short getWeight() {
        return weight;
    }

}
