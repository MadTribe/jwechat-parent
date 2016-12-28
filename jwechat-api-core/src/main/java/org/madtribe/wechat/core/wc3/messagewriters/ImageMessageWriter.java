package org.madtribe.wechat.core.wc3.messagewriters;

import org.madtribe.wechat.core.messageparserregistry.OutboundPayloadWriter;
import org.madtribe.wechat.core.messages.ImageMessage;
import org.madtribe.wechat.core.messages.TextMessage;
import org.madtribe.wechat.core.messages.inbound.request.MessagePayload;
import org.madtribe.wechat.core.wechatxml.WXmlDocument;

public class ImageMessageWriter implements OutboundPayloadWriter<ImageMessage> {

	@Override
	public void write(ImageMessage message, WXmlDocument doc) {
		doc.el("Image",  ( WXmlDocument image) -> {
			
			doc.el("MediaId",  ( WXmlDocument mediaId) -> {
			
				mediaId.cdata(message.getMediaId());
			});
		});
		
	}

}
