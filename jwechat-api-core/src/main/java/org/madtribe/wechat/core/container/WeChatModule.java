package org.madtribe.wechat.core.container;


import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.madtribe.wechat.core.client.DefaultHttpRequestUtils;
import org.madtribe.wechat.core.client.IHttpRequestUtils;
import org.madtribe.wechat.core.client.accesstoken.IAccessTokenStorage;
import org.madtribe.wechat.core.client.accesstoken.InMemoryAccessTokenHolder;
import org.madtribe.wechat.core.configuration.WeChatConfiguration;
import org.madtribe.wechat.core.messageparserregistry.*;
import org.madtribe.wechat.core.oauth2.DebugStateHandler;
import org.madtribe.wechat.core.oauth2.OAuth2UserStateHandler;
import org.madtribe.wechat.core.streamparsers.WeChatInboundMessageParser;
import org.madtribe.wechat.core.streamparsers.WeChatOutboundResponseWriter;
import org.madtribe.wechat.core.wc3.messageparsers.WeChatInboundRequestW3CParser;
import org.madtribe.wechat.core.wc3.messagewriters.InboundResponseMessageWriter;
import org.madtribe.wechat.core.wechatxml.WXmlDocument;
import org.madtribe.wechat.core.wechatxml.WXmlDocumentImpl;

/**
 * Created by paul.smout on 08/04/2016.
 */
public class WeChatModule extends AbstractModule {


    private WeChatConfiguration configuration;

    public WeChatModule( WeChatConfiguration configuration) {

        this.configuration = configuration;
    }

    @Override
    protected void configure() {
    	bind(InboundPayloadParserRegistry.class).to(DefaultInboundPayloadParserRegistry.class);
    	bind(WeChatInboundMessageParser.class).to(WeChatInboundRequestW3CParser.class);
    	bind(MessagePayloadWriterRegistry.class).to(DefaultPayloadWriterRegistry.class);
        bind(WeChatConfiguration.class).toInstance(configuration);
        bind(WeChatOutboundResponseWriter.class).to(InboundResponseMessageWriter.class);   
        configureOAuth2StateHandler();
        configureClient();
    }

	protected void configureOAuth2StateHandler() {
		bind(OAuth2UserStateHandler.class).to(DebugStateHandler.class);
	}

    protected void configureClient(){
        bind(IHttpRequestUtils.class).to(DefaultHttpRequestUtils.class);
        bind(IAccessTokenStorage.class).to(InMemoryAccessTokenHolder.class);
    }

    @Provides
    private WXmlDocument provideTransactionLog() {
     
      return new WXmlDocumentImpl();
    }


}
