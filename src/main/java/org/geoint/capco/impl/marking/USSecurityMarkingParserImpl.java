package org.geoint.capco.impl.marking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.geoint.capco.marking.InvalidSecurityMarkingException;
import org.geoint.capco.SecurityPolicy;
import org.geoint.capco.marking.USSecurityMarking;
import static org.geoint.capco.marking.USSecurityMarking.*;
import org.geoint.capco.marking.USSecurityMarkingBuilder;
import org.geoint.capco.marking.Country;
import org.geoint.capco.marking.SecurityMarking;
import static org.geoint.capco.marking.SecurityMarking.*;
import org.geoint.capco.spi.SecurityMarkingParser;

/**
 * parse/validate a US security marking.
 *
 * CLASSIFICATION//SCI/SCI-SUBCONTROL//SAP//AEA//FGI//DISSEM/DISSEM//OTHER
 * DISSEM
 * <p>
 * This parser is thread-safe.
 */
public class USSecurityMarkingParserImpl extends SecurityMarkingParser<USSecurityMarking> {

    public USSecurityMarkingParserImpl(SecurityPolicy policy) {
        super(policy);
    }

    @Override
    public USSecurityMarking parse(SecurityMarking context, String marking)
            throws InvalidSecurityMarkingException {
        if (marking == null) {
            throw new InvalidSecurityMarkingException(marking, "Marking is null.");
        }

        String[] components = marking.split(COMPONENT_SEPARATOR);

        if (components.length == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("'")
                    .append(marking)
                    .append("' is not a valid security marking within the "
                            + "Security Policy '")
                    .append(policy().getName())
                    .append("'.");
            throw new InvalidSecurityMarkingException(marking, sb.toString());
        }

        final USSecurityMarkingBuilder mb = policy().builder();

        //component 0 should be the classification component
        mb.setClassification(components[0].toUpperCase());

        //component 1 starts the optional components
        for (int c = 1; c < components.length; c++) {
            final String componentString = components[c].toUpperCase();
            if (componentString.startsWith(SAP_PORTION_IDENTIFIER)
                    || componentString.startsWith(SAP_BANNER_IDENTIFIER)) {
                //grab the SAP program names, code words, or digraph/trigraphs
                //after the component header
                final String sapContent = componentString.substring(componentString.indexOf("-") + 1);
                mb.addSAP(sapContent.split(SUBCOMPONENT_SLASH_SEPARATOR));
            } else if (componentString.startsWith(FGI_IDENTIFIER)) {
                String[] countries = componentString.substring(componentString.indexOf(FGI_IDENTIFIER) + FGI_IDENTIFIER.length()).split(" ");
                mb.addFGICountry(countries);
            } else if (!aeaBannerMarks.isEmpty()
                    && (aeaBannerMarks.containsKey(componentString)
                    || aeaPortionMarks.containsKey(componentString))) {

            } else {
                //is SCI or Dissemination component
                String[] controls = componentString.split(SUBCOMPONENT_SLASH_SEPARATOR);
                List<String> validControls = new ArrayList<>(); //either SCI or dissem controls

                if (c == 1) {
                    //this _could_ be SCI...give SCI first cracks =)
                    List<String> unknownSCI = new ArrayList<>(); //caputres if there were any erronous SCI controls
                    for (String control : controls) {
                        if (sciBannerMarks.containsKey(control)
                                || sciPortionMarks.containsKey(control)) {
                            validControls.add(control);
                        } else {
                            //either this is an invalid SCI...or this really is dissem controls
                            unknownSCI.add(control);
                        }
                    }

                    //now figure out if that component was SCI or dissem
                    if (!validControls.isEmpty()) {
                        if (!unknownSCI.isEmpty()) {
                            //was SCI...but had invalid control(s)
                            throw new InvalidSecurityMarkingException(
                                    marking,
                                    createControlError("SCI", unknownSCI));
                        }
                        //was SCI
                        mb.addSCI(validControls.toArray(new String[validControls.size()]));
                    } else {
                        //this is either dissem controls OR all the 
                        //SCI controls were invalid
                        List<String> unknownDiss = new ArrayList<>(); //caputres if there were any erronous dissemination controls
                        for (String control : controls) {
                            //may be a REL or DISPLAY dissemination control
                            //need special handling for this
                            if (control.startsWith(REL_PORTION_IDENTIFIER)) {
                                if (control.startsWith(RELTO_IDENTIFIER)) {
                                    String[] countries = control.substring(control.indexOf(RELTO_IDENTIFIER) + RELTO_IDENTIFIER.length()).split(COUNTRY_SEPARATOR);
                                    mb.addRelCountry(countries);
                                } else {
                                    //relative to context
                                    if (context == null) {
                                        throw new InvalidSecurityMarkingException(
                                                marking,
                                                "Security marking bearing "
                                                + "a 'REL' dissemination "
                                                + "marking must be parsed "
                                                + "in context of the overall"
                                                + "classification.");
                                    }
                                    if (!(context instanceof USSecurityMarking)) {
                                        throw new InvalidSecurityMarkingException(
                                                marking,
                                                "A 'REL' dissemination control can "
                                                + "only used used within the "
                                                + "context of a US "
                                                + "security marking.");
                                    }
                                    //add all the rel countries from the context marking
                                    for (Country rc : ((USSecurityMarking) context).getRelCountries()) {
                                        mb.addRelCountry(rc.getCode());
                                    }
                                }
                            } else if (control.startsWith(DISPLAY_IDENTIFIER)) {
                                String[] countries = control.substring(control.indexOf(DISPLAY_IDENTIFIER) + DISPLAY_IDENTIFIER.length()).split(COUNTRY_SEPARATOR);
                                mb.addDisplayCountry(countries);
                            } else if (control.startsWith(ACCM_IDENTIFIER)) {
                                String[] accm = control.substring(control.indexOf(ACCM_IDENTIFIER) + ACCM_IDENTIFIER.length()).split(SUBCOMPONENT_SLASH_SEPARATOR);
                                mb.addACCM(accm);
                            } else if (disBannerMarks.containsKey(control)
                                    || disPortionMarks.containsKey(control)) {
                                validControls.add(control);
                            } else {
                                unknownDiss.add(control);
                            }
                        }

                        if (!validControls.isEmpty()) {
                            //this is a dissemination component
                            if (!unknownDiss.isEmpty()) {
                                //but there were some errors
                                throw new InvalidSecurityMarkingException(
                                        marking,
                                        createControlError("Dissemination", unknownDiss));
                            }
                            mb.addDissemControl(validControls.toArray(new String[validControls.size()]));
                        } else {
                            //we have no idea what kind of component this 
                            //is suppose to be
                            StringBuilder sb = new StringBuilder();
                            sb.append("Unknown security marking comonent '")
                                    .append(componentString)
                                    .append("'");
                            throw new InvalidSecurityMarkingException(marking,
                                    sb.toString());
                        }
                    }
                }
            }
        }
        return mb.build();
    }

    @Override
    public USSecurityMarking parse(String marking)
            throws InvalidSecurityMarkingException {
        return parse(null, marking);
    }

    private String createControlError(String type, List<String> invalidControl) {
        //it was an SCI block...but it also contained 
        //unknown SCI.  create super-complex error
        final boolean multiErrors = (invalidControl.size() > 1);
        StringBuilder sb = new StringBuilder();
        sb.append(type)
                .append(" contained ");
        if (!multiErrors) {
            sb.append("an ");
        }
        sb.append("unknown SCI ");
        if (multiErrors) {
            sb.append("controls: ");
            Iterator<String> i = invalidControl.iterator();
            while (i.hasNext()) {
                sb.append("'").append(i.next()).append("'");
                if (i.hasNext()) {
                    sb.append(",");
                }
            }
        } else {
            sb.append("control: ");
            sb.append(invalidControl.get(0));
        }
        return sb.toString();
    }
}
