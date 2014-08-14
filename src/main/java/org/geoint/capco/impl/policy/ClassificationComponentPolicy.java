package org.geoint.capco.impl.policy;

import org.geoint.capco.InvalidSecurityMarkingException;
import org.geoint.capco.marking.ClassificationComponent;
import org.geoint.capco.marking.SecurityMarking;

/**
 *
 */
public class ClassificationComponentPolicy
        extends ComponentPolicy<ClassificationComponent> {

    @Override
    public boolean isComponentString(String component) {
        return (bannerIndex.containsKey(component) || bannerIndex.containsKey(component));
    }

    @Override
    public void validate(SecurityMarking marking) throws InvalidSecurityMarkingException {
        if (!components.contains(marking.getClassification())) {
            throw new InvalidSecurityMarkingException(marking.toString(),
                    "Security policy does not permit the classification '"
                    + marking.getClassification().getBanner() + "'");
        }
    }

    @Override
    public ClassificationComponent[] getAvailable(SecurityMarking marking) {
        if (marking.getClassification() == null) {
            return components.toArray(new ClassificationComponent[components.size()]);
        }
        return new ClassificationComponent[0];
    }

}
