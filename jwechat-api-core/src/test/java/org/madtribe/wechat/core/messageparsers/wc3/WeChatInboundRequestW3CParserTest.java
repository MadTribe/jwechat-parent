package org.madtribe.wechat.core.messageparsers.wc3;

import org.junit.Before;
import org.junit.Test;
import org.madtribe.wechat.core.messages.inbound.request.InboundRequest;

import java.io.InputStream;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by paul.smout on 15/04/2016.
 */
public class WeChatInboundRequestW3CParserTest {

    private WeChatInboundRequestW3CParser weChatInboundRequestW3CParser;

    @Before
    public void setup(){
        weChatInboundRequestW3CParser = new WeChatInboundRequestW3CParser();
    }

    @Test
    public void should_be_able_to_parse_a_text_message() throws Exception {
        InboundRequest inboundRequest = weChatInboundRequestW3CParser.parse(loadFixtureAsInputStream("inbound_messages/text_message_1.xml"));

        assertThat(inboundRequest, notNullValue());
        assertThat(inboundRequest.getSender(), equalTo("or4wgt3dwZQEXXyQxTGJBUlgYOJY") );

        // FIX This test next
        assertThat(inboundRequest.getId(), equalTo("6273080811757834052") );
    }

    private InputStream loadFixtureAsInputStream(String s) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(s);
        assertNotNull("Fixture Not Found",is);
        return is;
    }
}
