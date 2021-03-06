package org.madtribe.wechat.core.wc3.messageparsers;

import java.util.Optional;

import org.madtribe.wechat.core.messageparserregistry.InboundPayloadParser;
import org.madtribe.wechat.core.messages.TextMessage;
import org.madtribe.wechat.core.messages.inbound.request.MessagePayload;
import org.madtribe.wechat.core.streamparsers.MessageParsingException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class AbstractPayloadParser implements InboundPayloadParser<Element>{

	protected Node getElementByName(Element element, String name) throws MessageParsingException {
	    NodeList nodeList = element.getElementsByTagName(name);
	    if (nodeList.getLength() == 1){
	        return nodeList.item(0);
	    } else {
	        throw new MessageParsingException("Incorrect number of Elements called " + name + " found " + nodeList.getLength());
	    }
	}

}