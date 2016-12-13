package org.madtribe.wechat.core.messageparsers;

import java.util.HashMap;
import java.util.Optional;

import org.madtribe.wechat.core.constants.MessageTypes;
import org.madtribe.wechat.core.messageparsers.wc3.InboundPayloadParser;
import org.madtribe.wechat.core.messageparsers.wc3.InboundTextMessageParser;

public class DefaultInboundPayloadParserRegistry implements InboundPayloadParserRegistry {
	
	private HashMap<String, InboundPayloadParser> parsers = new HashMap<>(); ;
	
	public DefaultInboundPayloadParserRegistry(){
		parsers.put(MessageTypes.TEXT_MESSAGE_TYPE, new InboundTextMessageParser());
	}

	@Override
	public Optional<InboundPayloadParser> lookup(String messageType) {
		return Optional.of(parsers.get(messageType));
	}

}
