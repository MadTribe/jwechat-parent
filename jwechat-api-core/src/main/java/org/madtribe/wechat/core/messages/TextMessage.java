package org.madtribe.wechat.core.messages;

import org.madtribe.wechat.core.messages.inbound.request.InboundPayload;

public class TextMessage implements InboundPayload {

	private final String content;
	public TextMessage(String string) {
		this.content = string;
	}
	
	public String getContent() {
		return content;
	}
	

}
