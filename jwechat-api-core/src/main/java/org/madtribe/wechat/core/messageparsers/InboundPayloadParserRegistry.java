package org.madtribe.wechat.core.messageparsers;

import java.util.Optional;

import org.madtribe.wechat.core.messageparsers.wc3.InboundPayloadParser;

public interface InboundPayloadParserRegistry {

	public Optional<InboundPayloadParser> lookup(String messageType);

}
