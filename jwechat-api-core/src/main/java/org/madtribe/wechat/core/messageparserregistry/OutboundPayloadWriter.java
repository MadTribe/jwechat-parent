package org.madtribe.wechat.core.messageparserregistry;

import java.util.Optional;

import org.madtribe.wechat.core.messages.inbound.request.MessagePayload;
import org.madtribe.wechat.core.streamparsers.MessageParsingException;
import org.madtribe.wechat.core.wechatxml.WXmlDocument;

public interface OutboundPayloadWriter<K extends MessagePayload> {
	
	void write(K content, WXmlDocument doc);

}
