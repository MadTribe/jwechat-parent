package org.madtribe.wechat.core.wc3.messagewriters;

import static org.junit.Assert.*;

import javax.xml.transform.TransformerException;

import org.junit.Test;
import org.madtribe.wechat.core.configuration.WeChatConfiguration;
import org.madtribe.wechat.core.constants.MessageTypes;
import org.madtribe.wechat.core.container.JWeChatContanerMain;
import org.madtribe.wechat.core.messageparserregistry.DefaultPayloadWriterRegistry;
import org.madtribe.wechat.core.messages.ImageMessage;
import org.madtribe.wechat.core.messages.TextMessage;
import org.madtribe.wechat.core.messages.inbound.request.MessagePayload;
import org.madtribe.wechat.core.messages.inbound.response.InboundResponse;

public class InboundResponseMessageWriterTest {

	@Test
	public void testWriteImage() throws TransformerException, ClassNotFoundException {
		JWeChatContanerMain guiceMain = new JWeChatContanerMain( new WeChatConfiguration() );
		
		InboundResponseMessageWriter inboundResponseMessageWriter = guiceMain.get(InboundResponseMessageWriter.class);
		inboundResponseMessageWriter.writeToStream(new InboundResponse( MessageTypes.IMAGE_MESSAGE_TYPE, 
																		"MadTribe", 
																		"bob",
																		new ImageMessage("a_mediaId", "an_image_url")), 
												  System.out);;
	}

	@Test
	public void testWriteText() throws TransformerException, ClassNotFoundException {
		JWeChatContanerMain guiceMain = new JWeChatContanerMain( new WeChatConfiguration() );
		
		InboundResponseMessageWriter inboundResponseMessageWriter = guiceMain.get(InboundResponseMessageWriter.class);
		inboundResponseMessageWriter.writeToStream(new InboundResponse( MessageTypes.TEXT_MESSAGE_TYPE, 
																		"MadTribe", 
																		"bob",
																		new TextMessage("a message")), 
												  System.out);;
	}
	
}
