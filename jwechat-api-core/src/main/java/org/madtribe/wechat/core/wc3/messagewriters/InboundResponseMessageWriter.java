package org.madtribe.wechat.core.wc3.messagewriters;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import java.io.File;
import java.io.IOException;

import org.madtribe.wechat.core.constants.HeaderFieldNames;
import org.madtribe.wechat.core.messageparserregistry.MessagePayloadWriterRegistry;
import org.madtribe.wechat.core.messageparserregistry.OutboundPayloadWriter;
import org.madtribe.wechat.core.messages.inbound.request.InboundRequest;
import org.madtribe.wechat.core.messages.inbound.response.InboundResponse;
import org.madtribe.wechat.core.streamparsers.WeChatOutboundResponseWriter;
import org.madtribe.wechat.core.wc3.messageparsers.WeChatInboundRequestW3CParser;
import org.madtribe.wechat.core.wechatxml.WXmlDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Note from what I can tell the Wechat message response is not valid xml So we
 * are just using a StringBuilder for now.
 * 
 * @author paulsmout
 *
 */
public class InboundResponseMessageWriter implements WeChatOutboundResponseWriter {

	private static final Logger LOGGER = LoggerFactory.getLogger(WeChatInboundRequestW3CParser.class);
	
	@Inject
	private Provider<WXmlDocument> wxmlDocumentProvider;
	
	@Inject
	private  MessagePayloadWriterRegistry messagePayloadWriterRegistry;

	
	public InboundResponseMessageWriter() {
		this.messagePayloadWriterRegistry = messagePayloadWriterRegistry;
	}

	@Override
	public void writeToStream(InboundResponse myBean, OutputStream entityStream) {
		WXmlDocument doc = wxmlDocumentProvider.get();
		LOGGER.debug("Writing Bean to stream {}", myBean);
		
		doc.el("xml", (WXmlDocument xml) -> {
            xml.el(HeaderFieldNames.ToUserName.name(), (WXmlDocument toUserName) -> {
            	toUserName.cdata(myBean.getToUserName());
			});

            xml.el(HeaderFieldNames.FromUserName.name(), (WXmlDocument fromUserName) -> {
            	fromUserName.cdata(myBean.getFromUserName());
			});
            xml.el(HeaderFieldNames.CreateTime.name(), (WXmlDocument createTime) -> {
				createTime.number(myBean.getCreateTime());
			});
            
            String messageType = myBean.getMessageType();
            
            xml.el(HeaderFieldNames.MsgType.name(), (WXmlDocument msgType) -> {
            	msgType.cdata( messageType);
			});
            
            Optional<OutboundPayloadWriter> payloadWriter = messagePayloadWriterRegistry.lookup(messageType);
            LOGGER.debug("Payload writer present {}", payloadWriter.isPresent());
            if (payloadWriter.isPresent()){
            	LOGGER.debug("Payload writer is {}", payloadWriter.get().getClass());
            	payloadWriter.get().write(myBean.getPayload(), doc);
            }
            
		});

		try {
			LOGGER.debug("Will write {} ",doc.toXMLish());
			entityStream.write(doc.toXMLish().getBytes(Charset.forName("UTF-8")));
			LOGGER.debug("Will wrote {} bytes ",doc.toXMLish().getBytes(Charset.forName("UTF-8")).length);
		} catch (IOException e) {
			LOGGER.error("Error writing XML to stream", e);
		}
	}



}
