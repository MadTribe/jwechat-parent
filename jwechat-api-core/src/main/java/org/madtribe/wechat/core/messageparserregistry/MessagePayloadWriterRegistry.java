package org.madtribe.wechat.core.messageparserregistry;

import java.util.Optional;

public interface MessagePayloadWriterRegistry {

	public Optional<OutboundPayloadWriter> lookup(String messageType);

}
