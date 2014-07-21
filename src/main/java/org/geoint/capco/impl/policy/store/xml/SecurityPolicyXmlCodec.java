package org.geoint.capco.impl.policy.store.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.geoint.capco.SecurityPolicy;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 */
public class SecurityPolicyXmlCodec {

    public SecurityPolicy[] decode(InputStream in) throws IOException {
        try {
            SecurityPolicySAXHandler handler = new SecurityPolicySAXHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            parser.parse(in, handler);
            return handler.getPolicies();
        } catch (ParserConfigurationException | SAXException ex) {
            throw new IOException("Problem parsing SecurityPolicy XML", ex);
        }

    }

    public void encode(SecurityPolicy[] policies, OutputStream out)
            throws IOException {
        //TODO
        throw new UnsupportedOperationException("todo");
    }

    private class SecurityPolicySAXHandler extends DefaultHandler {

        private final List<SecurityPolicy> policies = new ArrayList<>();
        private final StringBuilder chars = new StringBuilder();

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            final String characters = getChars();


            chars.delete(0, chars.length());
        //TODO
            throw new UnsupportedOperationException("todo");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //TODO
            throw new UnsupportedOperationException("todo");
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            chars.append(ch, start, length);
        }

        private String getChars() {
            return chars.toString().trim();
        }

        public SecurityPolicy[] getPolicies() {
            return policies.toArray(new SecurityPolicy[policies.size()]);
        }
    }
}
