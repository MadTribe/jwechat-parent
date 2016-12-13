package org.madtribe.wechat.service;

import de.thomaskrille.dropwizard.environment_configuration.EnvironmentConfigurationFactoryFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.madtribe.wechat.core.messagehandlers.WeChatInboundRequestReader;
import org.madtribe.wechat.core.resources.WeChatEntryPoint;
import org.madtribe.wechat.service.container.GuiceMain;
import org.madtribe.wechat.service.resources.RootResource;


/**
 * Created by paul.smout on 04/04/2016.
 */
public class WechatExampleApplication extends Application<WechatExampleConfig> {
    private GuiceMain guiceMain;

    public static void main(String[] args) throws Exception {

        new WechatExampleApplication().run(args);
    }

    @Override
    public String getName() {
        return "wechat-example";
    }

    @Override
    public void initialize(Bootstrap<WechatExampleConfig> bootstrap) {
        bootstrap.setConfigurationFactoryFactory(new EnvironmentConfigurationFactoryFactory());
    }

    @Override
    public void run(WechatExampleConfig configuration, Environment environment) throws Exception {
        try {
            guiceMain = new GuiceMain(environment, configuration);
            
            
            System.err.println(">>>>>>>>>>>>>>>>>>" + configuration.getWeChatConfiguration().getWeChatToken());
            
            environment.jersey().register(guiceMain.get(WeChatInboundRequestReader.class));
            environment.jersey().register(new RootResource(configuration));
            environment.jersey().register(guiceMain.get(WeChatEntryPoint.class));
        } catch (Throwable t){
            t.printStackTrace();
        }
    }


}
