package org.geoint.capco.impl.policy;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.geoint.capco.ForeignSecurityMarking;
import org.geoint.capco.InvalidSecurityMarkingException;
import org.geoint.capco.JointSecurityMarking;
import org.geoint.capco.SecurityMarking;
import org.geoint.capco.SecurityMarkingBuilder;
import org.geoint.capco.SecurityPolicy;
import org.geoint.capco.USSecurityMarking;
import org.geoint.capco.spi.MutableSecurityPolicy;

/**
 * This policy implementation allows for structural knowledge of the CAPCO
 * specification, but does not contain logic for CAPCO controls (that are not
 * structural). Specific controls must be defined in the security policy
 * configuration.
 *
 * This class is thread-safe.
 */
public class SecurityPolicyImpl implements MutableSecurityPolicy {

    private final String name;

    //indicies
    private final Map<String, Country> countries = new HashMap<>(); //key is trigraph]
    private final Map<String, ClassificationComponent> classificationPortionMarks
            = new HashMap<>();
    private final Map<String, ClassificationComponent> classificationBannerMarks
            = new HashMap<>();
    private final Map<String, AeaComponent> aeaPortionMarks = new HashMap<>();
    private final Map<String, AeaComponent> aeaBannerMarks = new HashMap<>();
    private final Map<String, DisseminationComponent> disPortionMarks
            = new HashMap<>();
    private final Map<String, DisseminationComponent> disBannerMarks
            = new HashMap<>();
    private final Map<String, RelToComponent> relPortionMarks = new HashMap<>();
    private final Map<String, RelToComponent> relBannerMarks = new HashMap<>();
    private final Map<String, DisplayToComponent> displayPortionMarks
            = new HashMap<>();
    private final Map<String, DisplayToComponent> displayBannerMarks
            = new HashMap<>();
    private final Map<String, SapComponent> sapProgramDigraphMarks = new HashMap<>();
    private final Map<String, SapComponent> sapProgramNames = new HashMap<>();
    private final Map<String, SapComponent> sapCodeWords = new HashMap<>();
    private final Map<String, SciComponent> sciPortionMarks = new HashMap<>();
    private final Map<String, SciComponent> sciBannerMarks = new HashMap<>();
    private static final Charset MARKING_CHARSET = Charset.forName("UTF-8");

    //synchronization
    private final ReentrantReadWriteLock policyLock = new ReentrantReadWriteLock();
    private final Lock readLock = policyLock.readLock();
    private final Lock writeLock = policyLock.writeLock();

    //constants
    private static final String COMPONENT_SEPARATOR = "//";
    private static final String SUBCOMPONENT_SLASH_SEPARATOR = "/";
    private static final String SUBCOMPONENT_SPACE_SEPARATOR = " ";
    private static final String COUNTRY_SEPARATOR = ", ";
    private static final String SAP_BANNER_IDENTIFIER = "SPECIAL ACCESS REQUIRED-";
    private static final String SAP_PORTION_IDENTIFIER = "SAR-";
    private static final String REL_PORTION_IDENTIFIER = "REL ";
    private static final String RELTO_IDENTIFIER = "REL TO ";
    private static final String DISPLAY_IDENTIFIER = "DISPLAY ONLY ";
    private static final String HVSACO_IDENTIFIER = "HVSACO";
    private static final String FGI_IDENTIFIER = "FGI ";
    private static final String ACCM_IDENTIFIER = "ACCM-";

    public SecurityPolicyImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public SecurityMarkingBuilder builder() {
        return new SecurityMarkingBuilderImpl();
    }

    @Override
    public void add(Country country) {
        writeLock.lock();
        try {
            countries.put(country.getCode(), country);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void remove(Country country) {
        writeLock.lock();
        try {
            countries.remove(country.getCode());
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void add(ClassificationComponent classification) {
        writeLock.lock();
        try {
            this.classificationBannerMarks.put(classification.getBanner().intern(),
                    classification);
            this.classificationPortionMarks.put(classification.getPortion().intern(),
                    classification);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void remove(ClassificationComponent classification) {
        writeLock.lock();
        try {
            this.classificationBannerMarks.remove(classification.getBanner());
            this.classificationPortionMarks.remove(classification.getPortion());
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void add(AeaComponent aea) {
        writeLock.lock();
        try {
            this.aeaBannerMarks.put(aea.getBanner().intern(), aea);
            this.aeaPortionMarks.put(aea.getPortion().intern(), aea);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void remove(AeaComponent aea) {
        writeLock.lock();
        try {
            this.aeaBannerMarks.remove(aea.getBanner());
            this.aeaPortionMarks.remove(aea.getPortion());
        } finally {
            writeLock.unlock();
        }

    }

    @Override
    public void add(DisseminationComponent diss) {
        writeLock.lock();
        try {
            this.disBannerMarks.put(diss.getBanner().intern(), diss);
            this.disPortionMarks.put(diss.getPortion().intern(), diss);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void remove(DisseminationComponent diss) {
        writeLock.lock();
        try {
            this.disBannerMarks.remove(diss.getBanner());
            this.disPortionMarks.remove(diss.getPortion());
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void add(RelToComponent relTo) {
        writeLock.lock();
        try {
            this.relBannerMarks.put(relTo.getBanner().intern(), relTo);
            this.relPortionMarks.put(relTo.getPortion().intern(), relTo);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void remove(RelToComponent relTo) {
        writeLock.lock();
        try {
            this.relBannerMarks.remove(relTo.getBanner());
            this.relPortionMarks.remove(relTo.getPortion());
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void add(DisplayToComponent displayTo) {
        writeLock.lock();
        try {
            this.displayBannerMarks.put(displayTo.getBanner().intern(), displayTo);
            this.displayPortionMarks.put(displayTo.getPortion().intern(), displayTo);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void remove(DisplayToComponent displayTo) {
        writeLock.lock();
        try {
            this.displayBannerMarks.remove(displayTo.getBanner());
            this.displayPortionMarks.remove(displayTo.getPortion());
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void add(SapComponent sap) {
        writeLock.lock();
        try {
            this.sapCodeWords.put(sap.getBanner().intern(), sap);
            this.sapProgramDigraphMarks.put(sap.getPortion().intern(), sap);
            this.sapProgramNames.put(sap.getProgramName().intern(), sap);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void remove(SapComponent sap) {
        writeLock.lock();
        try {
            this.sapCodeWords.remove(sap.getBanner());
            this.sapProgramDigraphMarks.remove(sap.getPortion());
            this.sapProgramNames.remove(sap.getProgramName());
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void add(SciComponent sci) {
        writeLock.lock();
        try {
            this.sciBannerMarks.put(sci.getBanner().intern(), sci);
            this.sciPortionMarks.put(sci.getPortion().intern(), sci);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void remove(SciComponent sci) {
        writeLock.lock();
        try {
            this.sciBannerMarks.remove(sci.getBanner());
            this.sciPortionMarks.remove(sci.getPortion());
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Expects the byte[] is a UTF-8 string
     *
     * @param marking
     * @return
     * @throws InvalidSecurityMarkingException
     */
    @Override
    public SecurityMarking valueOf(byte[] marking) throws InvalidSecurityMarkingException {
        return valueOf(new String(marking, MARKING_CHARSET));
    }

    /**
     * Parses the CAPCO classification marking.
     *
     * @param marking
     * @return
     * @throws InvalidSecurityMarkingException
     */
    @Override
    public SecurityMarking valueOf(String marking)
            throws InvalidSecurityMarkingException {
        if (marking.startsWith(AbstractSecurityMarkingImpl.COMPONENT_SEPARATOR)) {
            if (marking.startsWith(
                    AbstractSecurityMarkingImpl.COMPONENT_SEPARATOR
                    + JointSecurityMarkingImpl.HEADER
            )) {
                return valueOfJoint(marking);
            } else {
                return valueOfNonUS(marking);
            }
        }
        return valueOfUS(marking);
    }

    /**
     * Parses CAPCO classification markings expected in the form:
     *
     * //JOINT [classification] [country codes]
     *
     * @param marking
     * @return
     * @throws InvalidSecurityMarkingException
     */
    private SecurityMarking valueOfJoint(String marking)
            throws InvalidSecurityMarkingException {
        throw new InvalidSecurityMarkingException(marking, "Joing markings not yet supported by policy.");
    }

    /**
     * Parses CAPCO classification markings expected in the form:
     *
     * //[country code] [non-U.S. classification]
     *
     * @param marking
     * @return
     * @throws InvalidSecurityMarkingException
     */
    private SecurityMarking valueOfNonUS(String marking)
            throws InvalidSecurityMarkingException {
        throw new InvalidSecurityMarkingException(marking, "NON-US markings not yet supported by policy.");
    }

    /**
     * Parses CAPCO classification marking expected in the form:
     *
     * CLASSIFICATION//SCI/SCI-SUBCONTROL//SAP//AEA//FGI//DISSEM/DISSEM//OTHER
     * DISSEM
     *
     * @param marking
     * @return
     * @throws InvalidSecurityMarkingException
     */
    private SecurityMarking valueOfUS(String marking)
            throws InvalidSecurityMarkingException {
        return new USMarkingParser().parse(marking);
    }

    @Override
    public boolean isPermitted(SecurityMarking m1, SecurityMarking m2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SecurityMarking merge(SecurityMarking... markings) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAllClassifications() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAllSCI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAllSAP() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAllFGICountries() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAllAEA() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAllRelCountries() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAllDisplayCountries() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAllDissemControls() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAllACCM() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Finite-state machine used to parse/validate a US security marking
     *
     * CLASSIFICATION//SCI/SCI-SUBCONTROL//SAP//AEA//FGI//DISSEM/DISSEM//OTHER
     * DISSEM
     *
     */
    private class USMarkingParser {

        private SecurityMarking context;

        public USMarkingParser() {
        }

        /**
         *
         * @param context the context of a security marking (ie parsing a
         * portion mark within a page marking)
         */
        public USMarkingParser(SecurityMarking context) {
            this.context = context;
        }
//        private USFsmComponent component;

        public SecurityMarking parse(String marking)
                throws InvalidSecurityMarkingException {
            if (marking == null) {
                throw new InvalidSecurityMarkingException(marking, "Marking cannot be null.");
            }

            String[] components = marking.split(COMPONENT_SEPARATOR);

            if (components.length == 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("'")
                        .append(marking)
                        .append("' is not a valid security marking within the "
                                + "Security Policy '")
                        .append(getName())
                        .append("'.");
                throw new InvalidSecurityMarkingException(marking, sb.toString());
            }

            final SecurityMarkingBuilder mb = builder();

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

//    private enum USFsmComponent {
//
//        //ordinal sequence is significant
//        classification, sci, sap, awa, fgi, dissem, other;
//    }
    private class SecurityMarkingBuilderImpl implements SecurityMarkingBuilder {

        AbstractSecurityMarkingImpl m;

        private USSecurityMarkingImpl getUSMarking()
                throws InvalidSecurityMarkingException {
            if (m == null) {
                m = new USSecurityMarkingImpl();
            } else if (!(m instanceof USSecurityMarkingImpl)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Cannot add US security marking component, builder "
                        + "is creating a ")
                        .append(m.getClass().getName())
                        .append(" marking");
                throw new InvalidSecurityMarkingException(m.toString(), sb.toString());
            }
            return (USSecurityMarkingImpl) m;
        }

        @Override
        public SecurityMarkingBuilder setOwningCountry(String countryCode) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public SecurityMarkingBuilder addContributingCountry(String... countryCode) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public SecurityMarkingBuilder setClassification(final String classification)
                throws InvalidSecurityMarkingException {
            readLock.lock();
            try {
                if (classificationPortionMarks.containsKey(classification)) {
                    m.classification = classificationPortionMarks.get(classification);
                } else if (classificationBannerMarks.containsKey(classification)) {
                    m.classification = classificationBannerMarks.get(classification);
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("'").append(classification).append("' is not a "
                            + "valid classification component for this policy.");
                    throw new InvalidSecurityMarkingException(m.toString(), sb.toString());
                }
            } finally {
                readLock.unlock();
            }
            return this;
        }

        @Override
        public SecurityMarkingBuilder addSCI(String... sci)
                throws InvalidSecurityMarkingException {
            USSecurityMarkingImpl usm = getUSMarking();
            readLock.lock();
            try {
                for (String control : sci) {
                    if (sciBannerMarks.containsKey(control)) {
                        usm.sci.add(sciBannerMarks.get(control));
                    } else if (sciPortionMarks.containsKey(control)) {
                        usm.sci.add(sciPortionMarks.get(control));
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Uknown SCI control '")
                                .append(control)
                                .append("' in security policy.");
                        throw new InvalidSecurityMarkingException(m.toString(), sb.toString());
                    }
                }
            } finally {
                readLock.unlock();
            }
            return this;
        }

        @Override
        public SecurityMarkingBuilder addSAP(String... sapNames)
                throws InvalidSecurityMarkingException {
            //validate SAP component...we do this first (before checking if 
            //this should be "multiple" because we want to validate regardless

            USSecurityMarkingImpl usm = getUSMarking();
            readLock.lock();
            try {
                boolean isMultiple = false;
                if (usm.sap.size() == 1 && usm.sap.iterator().next().isMultiple()) {
                    isMultiple = true;
                }

                for (String sn : sapNames) {
                    SapComponent sap = null;

                    if (sapProgramDigraphMarks.containsKey(sn)) {
                        sap = (sapProgramDigraphMarks.get(sn));
                    } else if (sapCodeWords.containsKey(sn)) {
                        sap = (sapCodeWords.get(sn));
                    } else if (sapProgramNames.containsKey(sn)) {
                        sap = (sapProgramNames.get(sn));
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("'")
                                .append(sn)
                                .append("' is not "
                                        + "a valid SAP program name, code word, or "
                                        + "digraph/trigraph for this policy.");
                        throw new InvalidSecurityMarkingException(m.toString(), sb.toString());
                    }

                    if (isMultiple) {
                        //disregard
                        continue;
                    }

                    usm.sap.add(sap);

                    //check if we're 4 or over now...we're using a Set, so it'll
                    //only store a SapComponent once
                    if (usm.sap.size() >= 4) {
                        isMultiple = true;
                        usm.sap.clear();
                        usm.sap.add(SapComponent.MULTIPLE);
                    }
                }
            } finally {
                readLock.unlock();
            }
            return this;
        }

        @Override
        public void setSpecialAccessChannelsOnly(boolean b) throws InvalidSecurityMarkingException {
            addDissemControl(HVSACO_IDENTIFIER);
        }

        @Override
        public SecurityMarkingBuilder setAEA(String aea) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public SecurityMarkingBuilder addFGICountry(String... countryCode) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public SecurityMarkingBuilder addRelCountry(String... countryCode) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public SecurityMarkingBuilder addDisplayCountry(String... countryCode) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public SecurityMarkingBuilder addDissemControl(String... controls)
                throws InvalidSecurityMarkingException {
            USSecurityMarkingImpl usm = getUSMarking();
            readLock.lock();
            try {
                for (String control : controls) {
                    if (disPortionMarks.containsKey(control)) {
                        usm.diss.add(disPortionMarks.get(control));
                    } else if (disBannerMarks.containsKey(control)) {
                        usm.diss.add(disBannerMarks.get(control));
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Unknown dissemination control '")
                                .append(control)
                                .append("' in security policy.");
                        throw new InvalidSecurityMarkingException(m.toString(), sb.toString());
                    }
                }
            } finally {
                readLock.unlock();
            }
            return this;
        }

        @Override
        public SecurityMarkingBuilder addACCM(String... accm
        ) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String[] getAvailableClassifications() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String[] getAvailableSCI() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String[] getAvailableSAP() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String[] getAvailableFGICountries() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String[] getAvailableAEA() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String[] getAvailableRelCountries() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String[] getAvailableDisplayCountries() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String[] getAvailableDissemCountrols() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String[] getAvailableACCM() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public SecurityMarking build() throws InvalidSecurityMarkingException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    private abstract class AbstractSecurityMarkingImpl implements SecurityMarking {

        public static final String COMPONENT_SEPARATOR = "//";
        ClassificationComponent classification;

        @Override
        public SecurityPolicy getPolicy() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public ClassificationComponent getClassification() {
            return classification;
        }

        @Override
        public SecurityMarking merge(SecurityMarking marking) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isPermitted(SecurityMarking marking) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public byte[] asBytes() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    private class USSecurityMarkingImpl extends AbstractSecurityMarkingImpl
            implements USSecurityMarking {

        Set<SapComponent> sap = new HashSet<>(); //SecurityMarkingBuilderImpl relies on this being a Set, if this is changed, update this
        Set<SciComponent> sci = new HashSet<>();
        Set<DisseminationComponent> diss = new HashSet<>();

        @Override
        public SciComponent[] getSCI() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public SapComponent[] getSAP() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isHVSACO() {
            return diss.contains(new DisseminationComponent(HVSACO_IDENTIFIER, HVSACO_IDENTIFIER));
        }

        @Override
        public AeaComponent getAEA() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Country[] getFGICountries() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Country[] getRelCountries() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Country[] getDisplayCountries() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public DisseminationComponent[] getDissemControls() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public AccmComponent[] getAccm() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String asPortion() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String asBanner() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    private class JointSecurityMarkingImpl extends USSecurityMarkingImpl
            implements JointSecurityMarking {

        public static final String HEADER = "//JOINT";

        @Override
        public Country[] getContributingCountries() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String asPortion() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String asBanner() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private class ForeignSecurityMarkingImpl extends AbstractSecurityMarkingImpl
            implements ForeignSecurityMarking {

        @Override
        public String asPortion() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String asBanner() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Country getOriginatingCountry() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
}
