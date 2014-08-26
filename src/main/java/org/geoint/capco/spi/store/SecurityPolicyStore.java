package org.geoint.capco.spi.store;

import org.geoint.capco.impl.policy.ComponentPolicy;
import org.geoint.capco.impl.policy.ComponentRestriction;
import org.geoint.capco.impl.policy.SapComponentPolicy;

/**
 * SPI interface for retrieving and persisting SecurityPolicy settings.
 *
 */
public interface SecurityPolicyStore {

    String[] getPolicyName
    SecurityPolicyImpl[] getPolicies();
    
    SecurityPolicyImpl getPolicy(String policy);
    
    String getPolicyName();

    /**
     * Return all the potential classification values based on the policy and
     * the requesting users permissions.
     *
     * @return
     */
    ComponentPolicy getClassificationPolicy();

    /**
     * Return all the potential SCI values based on the policy and the
     * requesting users permissions.
     *
     * @return
     */
    ComponentPolicy getSCIPolicy();

    /**
     * Return all the potential SAP values based on the policy and the
     * requesting users permissions.
     *
     * @return
     */
    SapComponentPolicy getSAPPolicy();

    /**
     * Return all the potential FGI country codes based on the policy and the
     * requesting users permissions.
     *
     * @return
     */
    ComponentPolicy getFGIPolicy();

    /**
     * Return all the AEA settings based on the policy and the requesting users
     * permissions.
     *
     * @return
     */
    ComponentPolicy getAEAPolicy();

    /**
     * Return all the potential REL country codes based on the policy and the
     * requesting users permissions.
     *
     * @return
     */
    ComponentPolicy getRelPolicy();

    /**
     * Return all the potential DISPLAY TO country codes based on the policy and
     * the requesting users permissions.
     *
     * @return
     */
    ComponentPolicy getDisplayPolicy();

    /**
     * Return all the potential dissemination controls based on the policy and
     * the requesting users permissions.
     *
     * @return
     */
    ComponentPolicy getDisseminationPolicy();

    /**
     * Return all the potential ACCM control words based on the policy and the
     * requesting users permissions.
     *
     * @return
     */
    ComponentPolicy getACCMPolicy();
    
    /**
     * Return the component restrictions for the policy.
     * 
     * @return 
     */
    ComponentRestriction[] getRestrictions();
}
