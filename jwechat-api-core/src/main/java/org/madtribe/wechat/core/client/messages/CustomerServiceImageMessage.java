package org.madtribe.wechat.core.client.messages;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerServiceImageMessage extends CustomerServiceMessage {

	@Valid
    @NotNull
	@JsonProperty("image")
    private ImageContent image;
	
	public CustomerServiceImageMessage(String toUser, String mediaId) {
		super(toUser, "image");
		this.image = new ImageContent(mediaId);
	}

	
	private class ImageContent {
		@Valid
	    @NotNull
		@JsonProperty("media_id")
	    private String mediaId;

		public ImageContent(String mediaId) {
			super();
			this.mediaId = mediaId;
		}

		public String getMediaId() {
			return mediaId;
		}
		
		
	}
	
}
