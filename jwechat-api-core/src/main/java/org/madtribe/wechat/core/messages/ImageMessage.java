package org.madtribe.wechat.core.messages;

import org.madtribe.wechat.core.messages.inbound.request.InboundPayload;

/**
 * Super class of every message with a MediaId
 * @author paulsmout
 */
public class ImageMessage extends MediaMessageParent {
	private String picUrl;
	
	public ImageMessage(String mediaId, String picUrl) {
		super(mediaId);
		this.picUrl = picUrl;
	}

	public String getPicUrl() {
		return picUrl;
	}

}
