package org.madtribe.wechat.core.messageparserregistry;

import java.util.HashMap;
import java.util.Optional;

import org.madtribe.wechat.core.constants.MessageTypes;
import org.madtribe.wechat.core.wc3.messageparsers.InboundImageMessageParser;
import org.madtribe.wechat.core.wc3.messageparsers.InboundTextMessageParser;
import org.madtribe.wechat.core.wc3.messagewriters.ImageMessageWriter;
import org.madtribe.wechat.core.wc3.messagewriters.TextMessageWriter;

/**
 * TODO, move this under the wc3 package. 
 * @author paulsmout
 *
 */
public class DefaultPayloadWriterRegistry implements MessagePayloadWriterRegistry {
	
	private HashMap<String, OutboundPayloadWriter> writers = new HashMap<>(); ;
	
	public DefaultPayloadWriterRegistry(){
		writers.put(MessageTypes.TEXT_MESSAGE_TYPE, new TextMessageWriter());
		writers.put(MessageTypes.IMAGE_MESSAGE_TYPE, new ImageMessageWriter());
	}

	@Override
	public Optional<OutboundPayloadWriter> lookup(String messageType) {
		return Optional.ofNullable(writers.get(messageType));
	}

}
