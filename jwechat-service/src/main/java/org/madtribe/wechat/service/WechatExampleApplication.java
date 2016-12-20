package org.madtribe.wechat.service;

import de.thomaskrille.dropwizard.environment_configuration.EnvironmentConfigurationFactoryFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import javax.ws.rs.core.Response;

import org.madtribe.wechat.core.container.JWeChatContanerMain;
import org.madtribe.wechat.core.messagehandlers.WeChatInboundRequestReader;
import org.madtribe.wechat.core.messages.TextMessage;
import org.madtribe.wechat.core.messages.inbound.request.InboundRequest;
import org.madtribe.wechat.core.resources.WeChatEntryPoint;
import org.madtribe.wechat.service.resources.RootResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Created by paul.smout on 04/04/2016.
 */
public class WechatExampleApplication extends Application<WechatExampleConfig> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatExampleApplication.class);

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

        WeChatEntryPoint entryPoint = configureEntryPoint(configuration, environment);

        // This is where you can register your WeChat message hanlders.
        // These can obviously be put in other classes as required.
        entryPoint.handle("text", (InboundRequest message) -> {
        	   return Response.ok("Client Code has received a text message: " + message).build();
        });


        entryPoint.handle("image", (InboundRequest message) -> {
        	  return Response.ok("Client Code has received an image message: " + message).build();
        });
    }

    private WeChatEntryPoint configureEntryPoint(WechatExampleConfig configuration, Environment environment) throws ClassNotFoundException {
        JWeChatContanerMain guiceMain = new JWeChatContanerMain( configuration.getWeChatConfiguration());
        environment.jersey().register(new RootResource(configuration));
        WeChatEntryPoint entryPoint = guiceMain.get(WeChatEntryPoint.class);
        environment.jersey().register(guiceMain.get(WeChatInboundRequestReader.class));
        environment.jersey().register(entryPoint);
        return entryPoint;
    }

}
