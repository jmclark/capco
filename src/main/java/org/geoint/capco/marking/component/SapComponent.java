package org.geoint.capco.marking.component;

/**
 * A Special Access Program (SAP) control marking has three potential tokens
 * that can be used in a classification marking:
 * <ol>
 * <li>the program name (ie <i>BUTTER POPCORN</i>)</li>
 * <li>the code word (ie <i>DAGGER</i>)</li>
 * <li>the programs assigned digraph/trigraph (ie <i>BP</i>)</li>
 * </ol>
 */
public class SapComponent extends MarkingComponent {

    /*
     * multiple flag is set when 4 or more SAP codes are in use
     */
    private final boolean multiple;
    private final String programName;
    public static final SapComponent MULTIPLE = new SapComponent(true);

    public SapComponent(String portionProgramName, String bannerCodeName, String programName) {
        super(portionProgramName, bannerCodeName);
        this.programName = programName;
        this.multiple = false;
    }

    private SapComponent(boolean multiple) {
        super("MULTIPLE", "MULTIPLE PROGRAMS");
        this.programName = "MULTIPLE PROGRAMS";
        this.multiple = true;
    }

    public String getProgramName() {
        return programName;
    }

    public boolean isMultiple() {
        return multiple;
    }

}
