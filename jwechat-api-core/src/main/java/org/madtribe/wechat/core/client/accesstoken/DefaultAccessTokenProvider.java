package org.madtribe.wechat.core.client.accesstoken;

import java.util.Optional;

import javax.inject.Inject;
import org.madtribe.wechat.core.client.WechatAPIClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultAccessTokenProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAccessTokenProvider.class);

	@Inject
	private IAccessTokenStorage accessTokenStorage;
	
	@Inject
	private WechatAPIClient wechatAPIClient;

	private static final long EXPIRY_GRACE_PERIOD_MILLIS = 1000 * 60 * 30;
	
	public Optional<AccessToken> obtainToken() {
		
		Optional<AccessToken> accessToken = accessTokenStorage.currentAccessToken();
		
		if (!accessToken.isPresent() || accessToken.get().timeInMillisToExpiry() < EXPIRY_GRACE_PERIOD_MILLIS){
			LOGGER.info("Requesting new access token");
			accessToken = wechatAPIClient.requestNewAccessToken();
			accessTokenStorage.storeAccessToken(accessToken.get());
		}
		LOGGER.debug("returning access token {}", accessToken.isPresent());
		return accessToken;
	}

}
