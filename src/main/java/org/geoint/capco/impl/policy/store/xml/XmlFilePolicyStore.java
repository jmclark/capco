package org.geoint.capco.impl.policy.store.xml;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.geoint.capco.spi.store.SecurityPolicyStore;

/**
 * Stores and retrieves security policies from an XML file.
 */
public class XmlFilePolicyStore{
//implements SecurityPolicyStore {
//
//    private final File source;
//    public static final String PROPERTY_XML_FILE = "capco.policy.store.xml.file";
//    private static final String DEFAULT_FILE_NAME = "capco-policies.xml";
//    private static final Logger logger
//            = Logger.getLogger(XmlFilePolicyStore.class.getName());
//
//    /**
//     * Attempts to load the policy from the file defined by a JVM property, or a
//     * default file.
//     */
//    public XmlFilePolicyStore() {
//        this(new File(System.getProperty(PROPERTY_XML_FILE, DEFAULT_FILE_NAME)));
//    }
//
//    public XmlFilePolicyStore(File source) {
//        this.source = source;
//    }
//
//    @Override
//    public SecurityPolicyImpl[] retrievePolicies() {
//        if (!source.exists()) {
//            return new SecurityPolicyImpl[0];
//        }
//
//        SecurityPolicyXmlCodec codec = new SecurityPolicyXmlCodec();
//        try (InputStream in = new BufferedInputStream(new FileInputStream(source))) {
//            return codec.decode(in);
//        } catch (IOException ex) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("Problems loading CAPCO policy definition from XML "
//                    + "policy store located at '")
//                    .append(source.getAbsoluteFile())
//                    .append("'");
//            logger.log(Level.SEVERE, sb.toString(), ex);
//            return new SecurityPolicyImpl[0];
//        }
//
//    }
//
//    @Override
//    public void save(SecurityPolicyImpl policy) throws IOException {
//        //policy xml file may contains multiple policies, which we need to preserve
//        Map<String, SecurityPolicyImpl> policies = new HashMap<>();
//        final String policyName = policy.getName();
//
//        for (SecurityPolicyImpl p : retrievePolicies()) {
//            //if this is the same as the passed in security policy, overwrite it
//            if (p.getName().contentEquals(policyName)) {
//                p = policy;
//            }
//            policies.put(policyName, p);
//        }
//
//        SecurityPolicyXmlCodec codec = new SecurityPolicyXmlCodec();
//        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(source))) {
//            codec.encode(policies.values().toArray(new SecurityPolicyImpl[policies.size()]), out);
//        }
//    }

}
