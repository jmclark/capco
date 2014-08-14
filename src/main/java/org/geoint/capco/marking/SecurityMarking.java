package org.geoint.capco.marking;

import org.geoint.capco.SecurityPolicy;

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
    ClassificationComponent getClassification();

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
     * Serialize SecurityMarking as CAPCO portion marking.
     *
     * @return
     */
    String asPortion();

    /**
     * Serialize SecurityMarking as CAPCO banner marking.
     * 
     * @return 
     */
    String asBanner();
    
    /**
     * Binary serialization of the marking.
     *
     * @return
     */
    byte[] asBytes();
}
