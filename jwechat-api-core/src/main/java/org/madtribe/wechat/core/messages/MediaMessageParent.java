package org.madtribe.wechat.core.messages;

import org.madtribe.wechat.core.messages.inbound.request.MessagePayload;

/**
 * Super class of every message with a MediaId
 * @author paulsmout
 */
public class MediaMessageParent implements MessagePayload {

	private final String mediaId;
	
	public MediaMessageParent(String mediaId) {
		this.mediaId = mediaId;
	}
	

	public String getMediaId() {
		return mediaId;
	}

	@Override
	public String toString() {
		return " mediaId=" + mediaId + " ";
	}
	

	
}
