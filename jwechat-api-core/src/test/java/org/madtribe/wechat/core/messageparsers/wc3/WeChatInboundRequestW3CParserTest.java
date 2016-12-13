package org.madtribe.wechat.core.messageparsers.wc3;

import org.junit.Before;

import org.junit.Test;
import org.madtribe.wechat.core.messageparsers.DefaultInboundPayloadParserRegistry;
import org.madtribe.wechat.core.messageparsers.InboundPayloadParserRegistry;
import org.madtribe.wechat.core.messages.TextMessage;
import org.madtribe.wechat.core.messages.inbound.request.InboundRequest;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.madtribe.wechat.core.constants.MessageTypes.*;
import org.mockito.MockitoAnnotations;
import org.w3c.dom.Node;

import java.io.InputStream;
import java.time.Instant;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**pay
 * Created by paul.smout on 15/04/2016.
 */

public class WeChatInboundRequestW3CParserTest {

    private WeChatInboundRequestW3CParser weChatInboundRequestW3CParser;
    
    @Mock
    InboundPayloadParserRegistry emptyInboundPayLoadParserRegistry;
    
    @Mock 
    InboundPayloadParser payloadParser;

    @Before
    public void setup(){
    	MockitoAnnotations.initMocks(this);
    	when(emptyInboundPayLoadParserRegistry.lookup(any())).thenReturn(Optional.empty());
        weChatInboundRequestW3CParser = new WeChatInboundRequestW3CParser(new DefaultInboundPayloadParserRegistry());
        
    }

    @Test
    public void should_be_able_to_parse_a_text_message() throws Exception {

        
        InboundRequest inboundRequest = weChatInboundRequestW3CParser.parse(loadFixtureAsInputStream("inbound_messages/text_message_1.xml"));
        
        assertThat(inboundRequest, notNullValue());
        assertThat(inboundRequest.getSender(), equalTo("or4wgt3dwZQEXXyQxTGJBUlgYOJY") );
        assertThat(inboundRequest.getId(), equalTo(6273080811757834052L) );
        assertThat(inboundRequest.getCreateTime(), equalTo(Instant.ofEpochMilli(1460565443L)) );
        
        assertThat(inboundRequest.getPayload(), notNullValue());
        assertThat(((TextMessage)inboundRequest.getPayload()).getContent(), equalTo("This is a test message"));
    }
    
    @Test
    public void should_throw_error_if_parser_not_found() throws Exception {
    	WeChatInboundRequestW3CParser parserToTest = new WeChatInboundRequestW3CParser(emptyInboundPayLoadParserRegistry);
       
        try {
        	InboundRequest inboundRequest = parserToTest.parse(loadFixtureAsInputStream("inbound_messages/text_message_1.xml"));
        	fail("Should hace thrown an error here");
        } catch (Exception e){
        	// nothing
        }
    }

    private InputStream loadFixtureAsInputStream(String s) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(s);
        assertNotNull("Fixture Not Found",is);
        return is;
    }
}
