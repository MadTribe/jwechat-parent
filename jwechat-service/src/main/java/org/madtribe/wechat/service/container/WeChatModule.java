package org.madtribe.wechat.service.container;


import com.google.inject.AbstractModule;
import org.madtribe.wechat.core.configuration.WeChatConfiguration;
import org.madtribe.wechat.service.WechatExampleConfig;

/**
 * Created by paul.smout on 08/04/2016.
 */
public class WeChatModule extends AbstractModule {


    private WechatExampleConfig configuration;

    public WeChatModule( WechatExampleConfig configuration) {

        this.configuration = configuration;
    }

    @Override
    protected void configure() {
        bind(WeChatConfiguration.class).toInstance(configuration.getWeChatConfiguration());
    }




}
