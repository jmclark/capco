package org.geoint.capco.impl.policy;

import org.geoint.capco.marking.MarkingComponent;

/**
 * Standard (generic) component policy features, used most often between the
 * components.
 *
 * @param <C>
 */
public class StandardComponentPolicy<C extends MarkingComponent>
        extends AbstractComponentPolicy<C> {


//    @Override
//    public void validate(SecurityMarking marking) throws InvalidSecurityMarkingException {
//
//        //ensure the relevant component(s) in the marking are actually 
//        //part of the accepted components in the policy
//        final C[] markingComponents = componentExtractor.extract(marking);
//        for (C c : markingComponents) {
//            if (!components.contains(c)) {
//                StringBuilder sb = new StringBuilder();
//                sb.append("Invalid component '")
//                        .append(c.getBanner())
//                        .append("' found in marking.");
//                throw new InvalidSecurityMarkingException(
//                        marking.toString(),
//                        sb.toString());
//            }
//        }
//
//        //validate against registered restrictions
//        for (ComponentRestriction r : restrictions) {
//            r.validate(marking);
//        }
//    }
//    /**
//     * Extracts the specific security marking component out of the
//     * SecurityMarking.
//     *
//     * @param <C>
//     */
//    public interface SecurityMarkingComponentExtractor<C extends MarkingComponent> {
//
//        C[] extract(SecurityMarking marking);
//    }
}
