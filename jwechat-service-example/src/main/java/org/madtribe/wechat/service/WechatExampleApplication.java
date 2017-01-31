package org.madtribe.wechat.service;

import de.thomaskrille.dropwizard.environment_configuration.EnvironmentConfigurationFactoryFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.Optional;

import javax.ws.rs.core.Response;

import org.madtribe.wechat.core.client.WechatAPI;
import org.madtribe.wechat.core.client.accesstoken.AccessToken;
import org.madtribe.wechat.core.client.errors.WeChatResponseError;
import org.madtribe.wechat.core.client.messages.CSImageMessage;
import org.madtribe.wechat.core.client.messages.CSTextMessage;
import org.madtribe.wechat.core.client.messages.MediaType;
import org.madtribe.wechat.core.client.responses.MediaUploadResponse;
import org.madtribe.wechat.core.constants.MessageTypes;
import org.madtribe.wechat.core.container.JWeChatContanerMain;
import org.madtribe.wechat.core.jersey.entityproviders.WeChatInboundRequestEntityProvider;
import org.madtribe.wechat.core.jersey.entityproviders.WeChatOutboundResponseEntityProvider;
import org.madtribe.wechat.core.messages.ImageMessage;
import org.madtribe.wechat.core.messages.TextMessage;
import org.madtribe.wechat.core.messages.inbound.request.InboundRequest;
import org.madtribe.wechat.core.messages.inbound.response.InboundResponse;
import org.madtribe.wechat.core.resources.WeChatEntryPoint;
import org.madtribe.wechat.service.resources.RootResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Created by paul.smout on 04/04/2016.
 */
public class WechatExampleApplication extends Application<WechatExampleConfig> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatExampleApplication.class);

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
    	guiceMain = new JWeChatContanerMain( configuration.getWeChatConfiguration());
    	
    	WechatAPI wechatAPI = guiceMain.get(WechatAPI.class);
    	
        WeChatEntryPoint entryPoint = configureEntryPoint(configuration, environment);
        
        
        Optional<MediaUploadResponse> mediaIdOpt = wechatAPI.uploadTemporaryMedia(this.getClass().getResourceAsStream("/ball.jpg"), MediaType.image);
        System.err.println("Uploaded ball and got media id" + mediaIdOpt.get());

        // This is where you can register your WeChat message hanlders.
        // These can obviously be put in other classes as required.
        entryPoint.handle("text", (InboundRequest message) -> {        	
        	
        	  Optional<AccessToken> accessToken = wechatAPI.getAccessToken();
        	
        	  System.err.println("Obtained Access Token: " + accessToken.get());
        	
        	  
        	  
        	  new Thread(() ->{
        		int numMessages = 11;
        		for (int i=1 ; i <= numMessages; i++){
        		   try {
        			  Thread.sleep(2000L);
        			  
        			  
        			  if (mediaIdOpt.isPresent()){
	
	        			  wechatAPI.sendCustomerServiceMessage(new CSImageMessage(message.getSender(),
	        					  mediaIdOpt.get().getMediaId() ));
        			  
        			  } else {
	        			  wechatAPI.sendCustomerServiceMessage(new CSTextMessage(message.getSender(),
	        					  											    "No media available " + i + " of "+ numMessages + "."));
        			  }
        			  
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (WeChatResponseError e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        	  }).start();
        	  
        	  
        	  return Response.ok(new InboundResponse( MessageTypes.IMAGE_MESSAGE_TYPE, 
        			  			 					  message.getRecipient(),
        			  			 					  message.getSender(),
        			  			 					  new TextMessage("Happy New Year!!"))).build();
        });


        entryPoint.handle("image", (InboundRequest message) -> {
        	
      	  return Response.ok(new InboundResponse( MessageTypes.TEXT_MESSAGE_TYPE, 
      			  			 					  message.getRecipient(), 
      			  			 					  message.getSender(),
      			  			 					  new TextMessage("The inbound image message was received"))).build();
        });
    }

    private WeChatEntryPoint configureEntryPoint(WechatExampleConfig configuration, Environment environment) throws ClassNotFoundException {
        
        environment.jersey().register(new RootResource(configuration));
        WeChatEntryPoint entryPoint = guiceMain.get(WeChatEntryPoint.class);
        environment.jersey().register(guiceMain.get(WeChatInboundRequestEntityProvider.class));
        environment.jersey().register(guiceMain.get(WeChatOutboundResponseEntityProvider.class));
        environment.jersey().register(entryPoint);
        return entryPoint;
    }

}
