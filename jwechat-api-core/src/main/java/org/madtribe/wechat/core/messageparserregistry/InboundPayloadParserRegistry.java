package org.madtribe.wechat.core.messageparserregistry;

import java.util.Optional;

public interface InboundPayloadParserRegistry {

	public Optional<InboundPayloadParser> lookup(String messageType);

}
