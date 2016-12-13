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


/**
 * Created by paul.smout on 04/04/2016.
 */
public class WechatExampleApplication extends Application<WechatExampleConfig> {
    private JWeChatContanerMain guiceMain;

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


            environment.jersey().register(new RootResource(configuration));


            guiceMain = new JWeChatContanerMain( configuration.getWeChatConfiguration());
            WeChatEntryPoint entryPoint = guiceMain.get(WeChatEntryPoint.class);
            environment.jersey().register(guiceMain.get(WeChatInboundRequestReader.class));
            environment.jersey().register(entryPoint);

            entryPoint.handle("text", (InboundRequest message) -> {
            	System.err.println("Client Code has received a message: " + message);

            	return Response.ok("Client Code has received a message: " + message).build();
            });


        } catch (Throwable t){
            t.printStackTrace();
        }
    }


}
