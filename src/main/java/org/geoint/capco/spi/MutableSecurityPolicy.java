package org.geoint.capco.spi;

import org.geoint.capco.SecurityPolicy;
import org.geoint.capco.impl.policy.AeaComponent;
import org.geoint.capco.impl.policy.ClassificationComponent;
import org.geoint.capco.impl.policy.Country;
import org.geoint.capco.impl.policy.DisplayToComponent;
import org.geoint.capco.impl.policy.DisseminationComponent;
import org.geoint.capco.impl.policy.RelToComponent;
import org.geoint.capco.impl.policy.SapComponent;
import org.geoint.capco.impl.policy.SciComponent;

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
