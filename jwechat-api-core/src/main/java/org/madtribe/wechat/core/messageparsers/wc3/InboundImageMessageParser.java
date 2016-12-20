package org.madtribe.wechat.core.messageparsers.wc3;

import java.util.Optional;

import org.madtribe.wechat.core.messages.ImageMessage;
import org.madtribe.wechat.core.messages.inbound.request.InboundPayload;
import org.madtribe.wechat.core.streamparsers.MessageParsingException;
import org.w3c.dom.Element;

public class InboundImageMessageParser extends InboundMediaMessageParser{
	public static final String PIC_URL = "PicUrl";
	
	@Override
	public Optional<InboundPayload> parse(Element content) throws MessageParsingException {
		
		return Optional.of(new ImageMessage(getMediaId(content), 
								getElementByName(content, PIC_URL).getTextContent() ));
	}

}
