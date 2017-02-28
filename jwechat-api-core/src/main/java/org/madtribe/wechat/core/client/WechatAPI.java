package org.madtribe.wechat.core.client;

import java.io.InputStream;
import java.util.Optional;

import javax.inject.Inject;

import org.madtribe.wechat.core.client.accesstoken.AccessToken;
import org.madtribe.wechat.core.client.accesstoken.DefaultAccessTokenProvider;
import org.madtribe.wechat.core.client.errors.WeChatResponseError;
import org.madtribe.wechat.core.client.menu.Menu;
import org.madtribe.wechat.core.client.messages.CustomerServiceMessage;
import org.madtribe.wechat.core.client.messages.MediaType;
import org.madtribe.wechat.core.client.responses.MediaUploadResponse;
import org.madtribe.wechat.core.client.responses.StatusResponse;
import org.madtribe.wechat.core.client.responses.UserDetails;

/**
 * This class contains the user accessible Wechat functions. 
 * Most implementation is delegated to WechatAPIClient which also contains non user facing functions. 
 *
 * @author paulsmout
 *
 */
public class WechatAPI {
	@Inject 
	private WechatAPIClient wechatAPIClient;
	
	@Inject 
	private DefaultAccessTokenProvider accessTokenProvider;

	public Optional<AccessToken> getAccessToken() {
		
		return accessTokenProvider.obtainToken();
	}

	public void sendCustomerServiceMessage(CustomerServiceMessage message) throws WeChatResponseError{
		Optional<AccessToken> accessToken = wechatAPIClient.requestNewAccessToken();
		wechatAPIClient.sendMessage(accessToken, message);;
	
	}

	public Optional<MediaUploadResponse> uploadTemporaryMedia(InputStream inputStream, MediaType type) throws WeChatResponseError{
		Optional<AccessToken> accessToken = wechatAPIClient.requestNewAccessToken();
		
		return wechatAPIClient.uploadTemporaryMedia(accessToken, inputStream, type);
	
	}
	
	public Optional<StatusResponse> createMenu(Menu menu) throws WeChatResponseError{
		Optional<AccessToken> accessToken = wechatAPIClient.requestNewAccessToken();
		
		return wechatAPIClient.createMenu(accessToken, menu);
	
	}	

	public Optional<UserDetails> getUserDetails(String openId) throws WeChatResponseError{
		Optional<AccessToken> accessToken = wechatAPIClient.requestNewAccessToken();
		
		return wechatAPIClient.getUserDetails(accessToken, openId);
	
	}	
	
	
}
