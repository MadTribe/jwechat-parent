package org.madtribe.wechat.core.messageparsers.wc3;



import org.madtribe.wechat.core.constants.HeaderFieldNames;
import org.madtribe.wechat.core.messageparsers.InboundPayloadParserRegistry;
import org.madtribe.wechat.core.messages.inbound.request.InboundPayload;
import org.madtribe.wechat.core.messages.inbound.request.InboundRequest;
import org.madtribe.wechat.core.streamparsers.MessageParsingException;
import org.madtribe.wechat.core.streamparsers.WeChatInboundMessageParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.inject.Inject;
import javax.management.modelmbean.XMLParseException;
import javax.ws.rs.WebApplicationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Optional;

/**
 * Created by paul.smout on 15/04/2016.
 */
public class WeChatInboundRequestW3CParser implements WeChatInboundMessageParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatInboundRequestW3CParser.class);

    private static String NO_PAYLOAD_PARSSER_ERROR = "Cannot find parser for payload.";
    
    // create a new DocumentBuilderFactory
    private final DocumentBuilderFactory factory;
    
    private final InboundPayloadParserRegistry inboundPayLoadParserRegistry;
    
    @Inject
    public WeChatInboundRequestW3CParser(final InboundPayloadParserRegistry inboundPayLoadParserRegistry){
        factory = DocumentBuilderFactory.newInstance();
        this.inboundPayLoadParserRegistry = inboundPayLoadParserRegistry;
    }

    @Override
    public InboundRequest parse(InputStream inputStream) throws IOException, MessageParsingException {
        InboundRequest parsed = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(inputStream);

            Element element = document.getDocumentElement();

            LOGGER.info("Root Element is {}", document.toString());
            
            Node messageId = getElementByName(element, HeaderFieldNames.MsgId.name());
            Node fromUserName = getElementByName(element, HeaderFieldNames.FromUserName.name());
            Node createTime = getElementByName(element, HeaderFieldNames.CreateTime.name());
            Node type = getElementByName(element, HeaderFieldNames.MsgType.name());
            Node content = getElementByName(element, HeaderFieldNames.Content.name());
            
            Optional<InboundPayloadParser> payloadParserOpt = inboundPayLoadParserRegistry.lookup(type.getTextContent() );
            
            {
	            Optional<InboundPayload> payload = payloadParserOpt.orElseThrow( () -> new WebApplicationException()).parse(content);
	            
	            if (LOGGER.isDebugEnabled()){
			        LOGGER.debug("MsgId = {}, FromUser = {}, CreateTime = {} ", 
			        			 messageId.getTextContent(),
			        			 fromUserName.getTextContent(),
			        			 createTime.getTextContent());
	            }
	
	            parsed = new InboundRequest(Long.valueOf(messageId.getTextContent()),
	                                        fromUserName.getTextContent(),
	                                        null,
	                                        type.getTextContent(),
	                                        Instant.ofEpochMilli(Long.valueOf(createTime.getTextContent())),
	                                        payload.orElseThrow( () -> new WebApplicationException()));
            } 
        } catch (NumberFormatException e) {
            LOGGER.error("Error parsing numerical field", e );
            throw new WebApplicationException(e);   
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
