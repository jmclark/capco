package org.geoint.capco.spi;

import org.geoint.capco.SecurityPolicy;
import org.geoint.capco.marking.AeaComponent;
import org.geoint.capco.marking.ClassificationComponent;
import org.geoint.capco.marking.Country;
import org.geoint.capco.marking.DisplayToComponent;
import org.geoint.capco.marking.DisseminationComponent;
import org.geoint.capco.marking.RelToComponent;
import org.geoint.capco.marking.SapComponent;
import org.geoint.capco.marking.SciComponent;

/**
 *
 */
public interface MutableSecurityPolicy extends SecurityPolicy {

    void add(Country country);

    void remove(Country country);

    void add(ClassificationComponent classification);

    void remove(ClassificationComponent classification);

    void add(AeaComponent aea);

    void remove(AeaComponent aea);

    void add(DisseminationComponent diss);

    void remove(DisseminationComponent diss);

    void add(RelToComponent relTo);

    void remove(RelToComponent relTo);

    void add(DisplayToComponent displayTo);

    void remove(DisplayToComponent displayTo);

    void add(SapComponent sap);

    void remove(SapComponent sap);

    void add(SciComponent sci);

    void remove(SciComponent sci);

}
