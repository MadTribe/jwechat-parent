package org.madtribe.wechat.core.streamparsers;

import org.madtribe.wechat.core.messages.inbound.request.InboundRequest;
import org.xml.sax.SAXException;

import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * Created by paul.smout on 15/04/2016.
 */
public interface WeChatInboundMessageParser {

    Optional<InboundRequest> parse(InputStream inputStream) throws WebApplicationException, IOException, MessageParsingException;
}
