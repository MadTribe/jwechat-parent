package org.madtribe.wechat.core.messageparserregistry;

import java.util.Optional;

import org.madtribe.wechat.core.messages.inbound.request.MessagePayload;
import org.madtribe.wechat.core.streamparsers.MessageParsingException;

public interface InboundPayloadParser<K> {
	
	Optional<MessagePayload> parse(K content) throws MessageParsingException;

}
