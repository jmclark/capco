package org.geoint.capco;

/**
 * Intelligence Community (IC)/CAPCO classification marking.
 * 

 * 
 */
public interface SecurityMarking {

    /**
     * Returns the {@link SecurityPolicy} that this SecurityMarking has been
     * created under.
     *
     * @return
     */
    SecurityPolicy getPolicy();

    /**
     * Return the classification component of the marking.
     *
     * @return
     */
    String getClassification();

    /**
     * Merge the provided {@link SecurityMarking} with this marking, returning
     * the merged marking.
     *
     * @param marking
     * @return
     */
    SecurityMarking merge(SecurityMarking marking);

    /**
     * Determines if the provided {@link SecurityMarking} is "permitted" within
     * this marking.
     *
     * @param marking
     * @return
     */
    boolean isPermitted(SecurityMarking marking);

    /**
     * Stringified serialization of the marking.
     *
     * @return
     */
    String asString();

    /**
     * Binary serialization of the marking.
     *
     * @return
     */
    byte[] asBytes();
}
