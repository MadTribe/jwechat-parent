package org.madtribe.wechat.core.messageparsers;

import java.util.Optional;

public interface InboundPayloadParserRegistry {

	public Optional<InboundPayloadParser> lookup(String messageType);

}
