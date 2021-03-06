package org.madtribe.wechat.core.wc3.messageparsers;

import java.util.Optional;

import org.madtribe.wechat.core.messageparserregistry.InboundPayloadParser;
import org.madtribe.wechat.core.messages.TextMessage;
import org.madtribe.wechat.core.messages.inbound.request.MessagePayload;
import org.madtribe.wechat.core.streamparsers.MessageParsingException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public abstract class InboundMediaMessageParser extends AbstractPayloadParser  {
	public static final String MEDIA_ID = "MediaId";
	
	public String getMediaId(Element content) throws MessageParsingException {
		
		return getElementByName(content, MEDIA_ID).getTextContent();
	}
	
}
