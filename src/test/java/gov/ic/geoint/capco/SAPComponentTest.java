package gov.ic.geoint.capco;

import org.junit.Test;
import static org.junit.Assert.*;

public class SAPComponentTest {

    public void testSAPBannerWithProgramName() {
        final String marking = "TOP SECRET//SPECIAL ACCESS REQUIRED-BUTTERED POPCORN";

    }

    public void testSAPShortenedBannerWithProgramName() {
        final String marking = "TOP SECRET//SAR-BUTTERED POPCORN";
    }

    public void testSAPBannerWithCodeWord() {
        final String marking = "TOP SECRET//SPECIAL ACCESS REQUIRED-DAGGER";
    }

    public void testSAPShortenedBannerWithCodeWord() {
        final String marking = "TOP SECRET//SAR-DAGGER";
    }

    public void testSAPBannerWithCodeWordDiagraph() {
        final String marking = "TOP SECRET//SPECIAL ACCESS REQUIRED-BP";
    }

    public void testSAPShortenedBannerWithCodeWordDigraph() {
        final String marking = "TOP SECRET//SAR-BP";
    }

    public void testSAPBannerWithMultiple() {
        final String marking = "TOP SECRET//SPECIAL ACCESS REQUIRED-MULTIPLE PROGRAMS";
    }

    public void testSAPShortenedBannerWithMultiple() {
        final String marking = "TOP SECRET//SAR-MULTIPLE PROGRAMS";
    }

    public void testSAPBannerWithTwoProgramNames() {
        final String marking = "TOP SECRET//SPECIAL ACCESS REQUIRED-BUTTERED POPCORN/SWAGGER";
    }

    public void testSAPShortenedBannerWithTwoProgramNames() {
        final String marking = "TOP SECRET//SAR-BUTTERED POPCORN/SWAGGER";
    }

    public void testSAPBannerWithTwoCodeWords() {
        final String marking = "TOP SECRET//SPECIAL ACCESS REQUIRED-DAGGER/SWAGGER";
    }

    public void testSAPShortenedBannerWithTwoCodeWords() {
        final String marking = "TOP SECRET//SAP-DAGGER/SWAGGER";
    }

    public void testSAPBannerWithTwoCodeWordDigraphs() {
        final String marking = "TOP SECRET//SPECIAL ACECSS REQUIRED-BP/SW";
    }

    public void testSAPShortenedBannerWithTwoCodeWordDigraphs() {
        final String marking = "TOP SECRET//SAR-BP/SW";
    }

    public void testSAPPortionWithProgramName() {
        final String marking = "TS//SAR-BUTTERED POPCORN";
    }

    public void testSAPPortionWithCodeWord() {
        final String marking = "TS//SAR-DAGGER";
    }

    public void testSAPPortionWithCodeWordDigraph() {
        final String marking = "TS//SAR-BP";
    }

    public void testSAPPortionWithMultiple() {
        final String marking = "TS//SAR-MULTIPLE";
    }

    public void testSAPPortionWithTwoProgramNames() {
        final String marking = "TS//SAR-BUTTERED POPCORN/SWAGGER";
    }

    public void testSAPPortionWithTwoCodeWords() {
        final String marking = "TS//SAR-DAGGER/SWAGGER";
    }

    public void testSAPPortionWithTwoCodeWordDigraphs() {
        final String marking = "TS//SAR-BP/SW";
    }

}
