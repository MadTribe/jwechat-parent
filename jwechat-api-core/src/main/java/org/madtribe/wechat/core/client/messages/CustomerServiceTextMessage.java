package org.madtribe.wechat.core.client.messages;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerServiceTextMessage extends CustomerServiceMessage {

	@Valid
    @NotNull
	@JsonProperty("text")
    private TextContent text;
	
	public CustomerServiceTextMessage(String toUser, String message) {
		super(toUser, "text");
		this.text = new TextContent(message);
	}

	
	private class TextContent {
		@Valid
	    @NotNull
		@JsonProperty("content")
	    private String content;

		public TextContent(String content) {
			super();
			this.content = content;
		}

		public String getContent() {
			return content;
		}
		
		
	}
	
}
