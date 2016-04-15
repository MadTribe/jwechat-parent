package org.madtribe.wechat.core.messageparsers.wc3;


import org.madtribe.wechat.core.messageparsers.HeaderFieldNames;
import org.madtribe.wechat.core.messageparsers.MessageParsingException;
import org.madtribe.wechat.core.messageparsers.WeChatInboundMessageParser;
import org.madtribe.wechat.core.messages.inbound.request.InboundRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.management.modelmbean.XMLParseException;
import javax.ws.rs.WebApplicationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by paul.smout on 15/04/2016.
 */
public class WeChatInboundRequestW3CParser implements WeChatInboundMessageParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatInboundRequestW3CParser.class);

    // create a new DocumentBuilderFactory
    private final DocumentBuilderFactory factory;
    public WeChatInboundRequestW3CParser(){
        factory = DocumentBuilderFactory.newInstance();
    }

    @Override
    public InboundRequest parse(InputStream inputStream) throws IOException, MessageParsingException {
        InboundRequest parsed = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(inputStream);

            Element element = document.getDocumentElement();

            LOGGER.info("Root Element is {}", element);
            Node fromUserName = getElementByName(element, HeaderFieldNames.FromUserName.name());

            LOGGER.debug("From User is {}", fromUserName.getTextContent());

            parsed = new InboundRequest(-1,
                                        fromUserName.getTextContent(),
                                        null,
                                        null,
                                        null);

        } catch (ParserConfigurationException e) {
            LOGGER.error("Error creating parser", e );
            throw new WebApplicationException(e);
        } catch (SAXException e){
            LOGGER.error("Error Parsing XML", e );
            throw new WebApplicationException(e);
        }
        return parsed;
    }

    private Node getElementByName(Element element, String name) throws MessageParsingException {
        NodeList nodeList = element.getElementsByTagName(name);
        if (nodeList.getLength() == 1){
            return nodeList.item(0);
        } else {
            throw new MessageParsingException("Incorrect number of Elements called " + name + " found " + nodeList.getLength());
        }
    }
}
