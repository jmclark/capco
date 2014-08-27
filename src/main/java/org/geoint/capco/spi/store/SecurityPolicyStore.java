package org.geoint.capco.spi.store;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.geoint.capco.impl.policy.ComponentPolicy;
import org.geoint.capco.impl.policy.ComponentRestriction;
import org.geoint.capco.impl.policy.SapComponentPolicy;
import org.geoint.capco.impl.policy.SecurityPolicyImpl;

/**
 * SPI interface for retrieving and persisting SecurityPolicy settings.
 *
 */
public interface SecurityPolicyStore {

    /**
     * Determine if the file is a policy store that can be loaded by this store
     * instance.
     *
     * @param fileName
     * @return
     */
    boolean isStore(String fileName);

    /*
     * load the policy from the InputStream
     */
    SecurityPolicyImpl load(InputStream in) throws IOException;

    /**
     * saves the policy to the OutputStream
     * 
     * @param policy
     * @throws IOException 
     */
    void save(SecurityPolicyImpl policy, OutputStream out) throws IOException;
    
//    /**
//     * return the name of the policy
//     *
//     * @return
//     */
//    String getPolicyName();
//
//    /**
//     * Return all the potential classification values based on the policy and
//     * the requesting users permissions.
//     *
//     * @return
//     */
//    ComponentPolicy getClassificationPolicy();
//
//    /**
//     * Return all the potential SCI values based on the policy and the
//     * requesting users permissions.
//     *
//     * @return
//     */
//    ComponentPolicy getSCIPolicy();
//
//    /**
//     * Return all the potential SAP values based on the policy and the
//     * requesting users permissions.
//     *
//     * @return
//     */
//    SapComponentPolicy getSAPPolicy();
//
//    /**
//     * Return all the potential FGI country codes based on the policy and the
//     * requesting users permissions.
//     *
//     * @return
//     */
//    ComponentPolicy getFGIPolicy();
//
//    /**
//     * Return all the AEA settings based on the policy and the requesting users
//     * permissions.
//     *
//     * @return
//     */
//    ComponentPolicy getAEAPolicy();
//
//    /**
//     * Return all the potential REL country codes based on the policy and the
//     * requesting users permissions.
//     *
//     * @return
//     */
//    ComponentPolicy getRelPolicy();
//
//    /**
//     * Return all the potential DISPLAY TO country codes based on the policy and
//     * the requesting users permissions.
//     *
//     * @return
//     */
//    ComponentPolicy getDisplayPolicy();
//
//    /**
//     * Return all the potential dissemination controls based on the policy and
//     * the requesting users permissions.
//     *
//     * @return
//     */
//    ComponentPolicy getDisseminationPolicy();
//
//    /**
//     * Return all the potential ACCM control words based on the policy and the
//     * requesting users permissions.
//     *
//     * @return
//     */
//    ComponentPolicy getACCMPolicy();
//
//    /**
//     * Return the component restrictions for the policy.
//     *
//     * @return
//     */
//    ComponentRestriction[] getRestrictions();
}
