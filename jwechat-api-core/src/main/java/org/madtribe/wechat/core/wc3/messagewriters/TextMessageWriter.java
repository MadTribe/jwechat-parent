package org.madtribe.wechat.core.wc3.messagewriters;

import org.madtribe.wechat.core.messageparserregistry.OutboundPayloadWriter;
import org.madtribe.wechat.core.messages.TextMessage;
import org.madtribe.wechat.core.messages.inbound.request.MessagePayload;
import org.madtribe.wechat.core.wechatxml.WXmlDocument;

public class TextMessageWriter implements OutboundPayloadWriter<TextMessage> {

	@Override
	public void write(TextMessage message, WXmlDocument doc) {
		doc.el("Content",  ( WXmlDocument content) -> {
			content.cdata(message.getContent());
		});
		
	}

}
