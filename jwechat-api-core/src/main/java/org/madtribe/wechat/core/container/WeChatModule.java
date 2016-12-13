package org.madtribe.wechat.core.container;


import com.google.inject.AbstractModule;

import org.madtribe.wechat.core.configuration.WeChatConfiguration;
import org.madtribe.wechat.core.messageparsers.*;
import org.madtribe.wechat.core.messageparsers.wc3.WeChatInboundRequestW3CParser;
import org.madtribe.wechat.core.streamparsers.WeChatInboundMessageParser;

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
        bind(WeChatConfiguration.class).toInstance(configuration);
    }




}
