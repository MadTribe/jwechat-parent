package org.madtribe.wechat.service;

import de.thomaskrille.dropwizard.environment_configuration.EnvironmentConfigurationFactoryFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.Arrays;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.madtribe.wechat.core.client.WechatAPI;
import org.madtribe.wechat.core.client.accesstoken.AccessToken;
import org.madtribe.wechat.core.client.errors.WeChatResponseError;
import org.madtribe.wechat.core.client.menu.ViewButton;
import org.madtribe.wechat.core.client.menu.ViewLimitedButton;
import org.madtribe.wechat.core.client.menu.LocationSelectButton;
import org.madtribe.wechat.core.client.menu.MediaIdButton;
import org.madtribe.wechat.core.client.menu.Menu;
import org.madtribe.wechat.core.client.menu.PicPhotoOrAlbumButton;
import org.madtribe.wechat.core.client.menu.PicSysPhotoButton;
import org.madtribe.wechat.core.client.menu.PicWeixinButton;
import org.madtribe.wechat.core.client.menu.ScanCodePushButton;
import org.madtribe.wechat.core.client.menu.ScanCodeWaitMsgButton;
import org.madtribe.wechat.core.client.menu.SubMenu;
import org.madtribe.wechat.core.client.messages.CustomerServiceImageMessage;
import org.madtribe.wechat.core.client.messages.CustomerServiceTextMessage;
import org.madtribe.wechat.core.client.messages.MediaType;
import org.madtribe.wechat.core.client.responses.MediaUploadResponse;
import org.madtribe.wechat.core.client.responses.UserDetails;
import org.madtribe.wechat.core.constants.MessageTypes;
import org.madtribe.wechat.core.container.JWeChatContanerMain;
import org.madtribe.wechat.core.jersey.entityproviders.WeChatInboundRequestEntityProvider;
import org.madtribe.wechat.core.jersey.entityproviders.WeChatOutboundResponseEntityProvider;
import org.madtribe.wechat.core.messages.ImageMessage;
import org.madtribe.wechat.core.messages.TextMessage;
import org.madtribe.wechat.core.messages.inbound.request.InboundRequest;
import org.madtribe.wechat.core.messages.inbound.response.InboundResponse;
import org.madtribe.wechat.core.messages.inbound.response.TextMessageResponse;
import org.madtribe.wechat.core.resources.OAuth2EntryPoint;
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
		guiceMain = new JWeChatContanerMain(configuration.getWeChatConfiguration());

		WechatAPI wechatAPI = guiceMain.get(WechatAPI.class);

		WeChatEntryPoint entryPoint = configureEntryPoint(configuration, environment);

		// Upload an image and get its media ID
		Optional<MediaUploadResponse> mediaIdOpt = wechatAPI
				.uploadTemporaryMedia(this.getClass().getResourceAsStream("/ball.jpg"), MediaType.image);


		Menu menu = new Menu(Arrays.asList(
									new SubMenu("Search Engines",
											    Arrays.asList(new ViewButton("Baidu","http://baidu.com")
												   		     // new MediaIdButton("MediaIdButton",mediaIdOpt.get().getMediaId()),
												   		     /* new ViewLimitedButton("ViewLimitedButton",mediaIdOpt.get().getMediaId())*/)
											    ),
									new SubMenu("System",
										    Arrays.asList(new ScanCodePushButton("ScanCodePushButton","key1"), 
											   		      new ScanCodeWaitMsgButton("ScanCodeWaitMsgButton","key2"),
											   		      new LocationSelectButton("LocationSelectButton","key6"))
										    ),
									new SubMenu("More",
										    Arrays.asList(new PicSysPhotoButton("PicSysPhotoButton","key3"),
											   		      new PicPhotoOrAlbumButton("PicPhotoOrAlbumButton","key4"),
											   		      new PicWeixinButton("PicWeixinButton","key5")
											   		      
										    )
									)
				));
		
		
		
		wechatAPI.createMenu(menu);
		
		
		// This is where you can register your WeChat message hanlders.
		// These can obviously be put in other classes as required.
		entryPoint.handle("text", (InboundRequest message) -> {
			try {
				Optional<AccessToken> accessToken = wechatAPI.getAccessToken();

				// Send a customer service message
				if (mediaIdOpt.isPresent()) {

					wechatAPI.sendCustomerServiceMessage(
							new CustomerServiceImageMessage(message.getSender(), mediaIdOpt.get().getMediaId()));

				} else {
					wechatAPI.sendCustomerServiceMessage(new CustomerServiceTextMessage(message.getSender(), "No media available "));
				}

				Optional<UserDetails> optUserDetails = wechatAPI.getUserDetails(message.getSender());
				
				System.out.println(optUserDetails.orElse(null));
				
				if (optUserDetails.isPresent()){
					return Response.ok(new TextMessageResponse(message, "Happy Year of the Rooster " + optUserDetails.get().getNickname() + "!")).build();
				}
			} catch (WeChatResponseError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Response.ok(new TextMessageResponse(message, "Happy Year of the Rooster")).build();
		});

		entryPoint.handle("image", (InboundRequest message) -> {

			return Response.ok(new TextMessageResponse(message, "An Image Message was received")).build();
		});
	}

	private WeChatEntryPoint configureEntryPoint(WechatExampleConfig configuration, Environment environment)
			throws ClassNotFoundException {

		environment.jersey().register(new RootResource(configuration));
		WeChatEntryPoint entryPoint = guiceMain.get(WeChatEntryPoint.class);
		environment.jersey().register(guiceMain.get(WeChatInboundRequestEntityProvider.class));
		environment.jersey().register(guiceMain.get(WeChatOutboundResponseEntityProvider.class));
		environment.jersey().register(guiceMain.get(OAuth2EntryPoint.class));
		environment.jersey().register(entryPoint);
		return entryPoint;
	}

	private void runSeveralTimes(Runnable run) {

	}

}
