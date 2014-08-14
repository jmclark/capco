
package org.geoint.capco.impl.policy;

import org.geoint.capco.SecurityPolicy;
import org.geoint.capco.USSecurityMarking;
import org.geoint.capco.spi.SecurityMarkingParser;

/**
 *
 */
public class USSecurityMarkingParserImpl extends SecurityMarkingParser<USSecurityMarking>{

    public USSecurityMarkingParserImpl(SecurityPolicy policy) {
        super(policy);
    }

    
    @Override
    public USSecurityMarking parse(USSecurityMarking context, String marking) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public USSecurityMarking parse(String marking) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
