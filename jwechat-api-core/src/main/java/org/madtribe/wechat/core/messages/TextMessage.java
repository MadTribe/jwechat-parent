package org.madtribe.wechat.core.messages;

import org.madtribe.wechat.core.messages.inbound.request.InboundPayload;

public class TextMessage implements InboundPayload {

	private final String content;
	public TextMessage(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "TextMessage [content=" + content + "]";
	}
	

	
}
