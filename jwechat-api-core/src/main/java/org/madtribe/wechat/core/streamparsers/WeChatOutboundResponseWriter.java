package org.madtribe.wechat.core.streamparsers;

import java.io.OutputStream;

import org.madtribe.wechat.core.messages.inbound.response.InboundResponse;

public interface WeChatOutboundResponseWriter {

	void writeToStream(InboundResponse myBean, OutputStream entityStream);

}
