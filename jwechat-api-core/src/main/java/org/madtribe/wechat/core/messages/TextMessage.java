package org.madtribe.wechat.core.messages;

import org.madtribe.wechat.core.messages.inbound.request.MessagePayload;

public class TextMessage implements MessagePayload {

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
