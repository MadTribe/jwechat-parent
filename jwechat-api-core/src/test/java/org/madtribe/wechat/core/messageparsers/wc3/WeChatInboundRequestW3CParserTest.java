package org.madtribe.wechat.core.messageparsers.wc3;

import org.junit.Before;
import org.junit.Test;
import org.madtribe.wechat.core.messageparsers.InboundPayloadParserRegistry;
import org.madtribe.wechat.core.messages.TextMessage;
import org.madtribe.wechat.core.messages.inbound.request.InboundRequest;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;
import java.time.Instant;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**pay
 * Created by paul.smout on 15/04/2016.
 */

public class WeChatInboundRequestW3CParserTest {

    private WeChatInboundRequestW3CParser weChatInboundRequestW3CParser;
    
    @Mock
    InboundPayloadParserRegistry inboundPayLoadParserRegistry;
    
    @Mock 
    InboundPayloadParser payloadParser;

    @Before
    public void setup(){
    	MockitoAnnotations.initMocks(this);
        weChatInboundRequestW3CParser = new WeChatInboundRequestW3CParser(inboundPayLoadParserRegistry);
    }

    @Test
    public void should_be_able_to_parse_a_text_message() throws Exception {
        when(inboundPayLoadParserRegistry.lookup("text")).thenReturn(Optional.of(payloadParser));
        when(payloadParser.parse(any())).thenReturn(Optional.of(new TextMessage("test")));
        
        InboundRequest inboundRequest = weChatInboundRequestW3CParser.parse(loadFixtureAsInputStream("inbound_messages/text_message_1.xml"));
        
        assertThat(inboundRequest, notNullValue());
        assertThat(inboundRequest.getSender(), equalTo("or4wgt3dwZQEXXyQxTGJBUlgYOJY") );
        assertThat(inboundRequest.getId(), equalTo(6273080811757834052L) );
        assertThat(inboundRequest.getCreateTime(), equalTo(Instant.ofEpochMilli(1460565443L)) );
        
        // FIX This test next
        assertThat(inboundRequest.getPayload(), notNullValue());
    }

    private InputStream loadFixtureAsInputStream(String s) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(s);
        assertNotNull("Fixture Not Found",is);
        return is;
    }
}
