package org.madtribe.wechat.core.messages.inbound.response;

import java.util.Date;

import org.madtribe.wechat.core.constants.MessageTypes;
import org.madtribe.wechat.core.messages.TextMessage;
import org.madtribe.wechat.core.messages.inbound.request.InboundRequest;
import org.madtribe.wechat.core.messages.inbound.request.MessagePayload;

/**
 * Created by paul.smout on 02/04/2016.
 */
public class TextMessageResponse extends InboundResponse{

	public TextMessageResponse( InboundRequest request, String content) {
		super(MessageTypes.TEXT_MESSAGE_TYPE, request.getRecipient(), request.getSender(), new TextMessage(content));
	}

}
