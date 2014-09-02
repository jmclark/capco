package org.geoint.capco.policy;

/**
 * A control which can be compared to additional controls within its category to
 * determine which control is most significant.
 */
public class WeightedControlPolicy extends StandardControlPolicy
        implements Comparable<WeightedControlPolicy> {

    private final int weight;

    public WeightedControlPolicy(String portion, String banner, int weight) {
        super(portion, banner);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(WeightedControlPolicy o) {
        return Integer.valueOf(weight).compareTo(o.weight);
    }

}
