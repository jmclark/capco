package org.geoint.capco;

/**
 * Indicates an object can contain classified data.
 */
public interface Classified {

    /**
     * Return the {@link SecurityMarking} for the data component.
     *
     * @return
     */
    SecurityMarking getSecurityMarking();

    /**
     * Return the {@link ClassificationAuthority} details for the classified
     * data.
     *
     * @return
     */
    ClassificationAuthority getClassificationAuthority();
}
