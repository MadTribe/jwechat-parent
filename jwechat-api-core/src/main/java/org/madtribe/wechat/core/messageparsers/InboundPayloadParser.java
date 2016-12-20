package org.madtribe.wechat.core.messageparsers;

import java.util.Optional;

import org.madtribe.wechat.core.messages.inbound.request.InboundPayload;
import org.madtribe.wechat.core.streamparsers.MessageParsingException;

public interface InboundPayloadParser<K> {
	
	Optional<InboundPayload> parse(K content) throws MessageParsingException;

}
