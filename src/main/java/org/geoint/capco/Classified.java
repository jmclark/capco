package org.geoint.capco;

import org.geoint.capco.marking.SecurityMarking;

/**
 * Indicates an object can contain classified data.
 */
public interface Classified {

    /**
     * Return the stringified {@link SecurityMarking} for the data component.
     *
     * @return
     */
    String getClassification();

    /**
     * Return the {@link ClassificationAuthority} details for the classified
     * data.
     *
     * @return
     */
    ClassificationAuthority getClassificationAuthority();

    /**
     * Returns the underlying {@link SecurityMarking} for the data component.
     *
     * Note: A SecurityMarking may be lazily created for an object, as the
     * stringified/binary representation of a SecurityMarking is often the
     * actual value persistently stored. If it isn't needed to use the
     * {@link SecurityMarking} itself, but just the String representation (ie to
     * display) it's often best to simply call the
     * {@link Classified#getClassification()} method instead.
     *
     * @return
     */
    SecurityMarking getSecurityMarking();
}
