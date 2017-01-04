package org.madtribe.wechat.core.client;

import java.util.Optional;

import javax.inject.Inject;

import org.madtribe.wechat.core.client.accesstoken.AccessToken;
import org.madtribe.wechat.core.configuration.WeChatConfiguration;

public class WechatAPIClient {
	@Inject
	private IHttpRequestUtils httpClient;
	
	@Inject
	private WeChatConfiguration config;	

	public Optional<AccessToken> requestNewAccessToken() {
		String tokenUrlforAppIdAndAppSecret = config.getWechatURLsConfig().getTokenUrlforAppIdAndAppSecret();
		String url = String.format(tokenUrlforAppIdAndAppSecret, config.getWeChatAppId(), config.getWeChatAppSecret());
		Optional<AccessToken> accesToken = httpClient.getAsObject(url,AccessToken.class);
		return accesToken;
	}
	
	
}
