package org.madtribe.wechat.core.messageparsers.wc3;

import java.util.Optional;

import org.madtribe.wechat.core.messages.inbound.request.InboundPayload;
import org.w3c.dom.Node;

public interface InboundPayloadParser {
	
	Optional<InboundPayload> parse(Node content);

}
