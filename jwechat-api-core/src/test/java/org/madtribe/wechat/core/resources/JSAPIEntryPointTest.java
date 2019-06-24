package org.madtribe.wechat.core.resources;

import org.junit.Before;
import org.junit.Test;
import org.madtribe.wechat.core.client.jsapi.JSAPITicket;
import org.madtribe.wechat.core.client.jsapi.JSAPITicketProvider;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JSAPIEntryPointTest {
    @InjectMocks
    private JSAPIEntryPoint jsapiEntryPoint;

    @Mock
    private JSAPITicketProvider jsapiTicketProvider;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testNullURL_returnsInvalidRequest() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Response response = jsapiEntryPoint.signURL(com.google.common.base.Optional.<String>absent(),
                                                    com.google.common.base.Optional.<Long>absent(),
                                                    com.google.common.base.Optional.<String>absent());
        assertEquals(JSAPIEntryPoint.NOT_ACCEPTABLE, response);
    }

    @Test
    public void testURL_NoJSAPITicket_returnsNotNull() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        when(jsapiTicketProvider.obtainToken()).thenReturn(Optional.empty());

        Response response = jsapiEntryPoint.signURL(com.google.common.base.Optional.of("http://mp.weixin.qq.com?params=value"),
                                                    com.google.common.base.Optional.of(1414587457L),
                                                    com.google.common.base.Optional.of("Wm3WZYTPz0wzccnW"));

        assertEquals( JSAPIEntryPoint.NOT_AUTHORIZED, response);
    }

    @Test
    public void tesURL_returnsNotNull() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        JSAPITicket ticket = mock(JSAPITicket.class);
        when(ticket.getTicket()).thenReturn("sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg");
        when(jsapiTicketProvider.obtainToken()).thenReturn(Optional.<JSAPITicket>of(ticket));

        Response response = jsapiEntryPoint.signURL(com.google.common.base.Optional.of("http://mp.weixin.qq.com?params=value"),
                com.google.common.base.Optional.of(1414587457L),
                com.google.common.base.Optional.of("Wm3WZYTPz0wzccnW"));

        assertEquals( "0f9de62fce790f9a083d5c99e95740ceb90c27ed", ((com.google.common.base.Optional<String>)response.getEntity()).get());
    }

}