package org.madtribe.wechat.core.client;

import java.util.Optional;

import javax.inject.Inject;

import org.madtribe.wechat.core.client.accesstoken.AccessToken;
import org.madtribe.wechat.core.client.accesstoken.DefaultAccessTokenProvider;
import org.madtribe.wechat.core.client.messages.CustomerServiceMessage;


public class WechatAPI {
	@Inject 
	private WechatAPIClient wechatAPIClient;
	
	@Inject 
	private DefaultAccessTokenProvider accessTokenProvider;

	public Optional<AccessToken> getAccessToken() {
		
		return accessTokenProvider.obtainToken();
	}

	public void sendCustomerServiceMessage(CustomerServiceMessage message){
		Optional<AccessToken> accessToken = wechatAPIClient.requestNewAccessToken();
		wechatAPIClient.sendMessage(accessToken, message);;
	
	}
	
	
}
