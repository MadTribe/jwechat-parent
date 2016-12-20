package org.madtribe.wechat.core.messageparsers;

import java.util.HashMap;
import java.util.Optional;

import org.madtribe.wechat.core.constants.MessageTypes;
import org.madtribe.wechat.core.messageparsers.wc3.InboundImageMessageParser;
import org.madtribe.wechat.core.messageparsers.wc3.InboundTextMessageParser;

/**
 * TODO, move this under the wc3 package. 
 * @author paulsmout
 *
 */
public class DefaultInboundPayloadParserRegistry implements InboundPayloadParserRegistry {
	
	private HashMap<String, InboundPayloadParser> parsers = new HashMap<>(); ;
	
	public DefaultInboundPayloadParserRegistry(){
		parsers.put(MessageTypes.TEXT_MESSAGE_TYPE, new InboundTextMessageParser());
		parsers.put(MessageTypes.IMAGE_MESSAGE_TYPE, new InboundImageMessageParser());
	}

	@Override
	public Optional<InboundPayloadParser> lookup(String messageType) {
		return Optional.ofNullable(parsers.get(messageType));
	}

}
