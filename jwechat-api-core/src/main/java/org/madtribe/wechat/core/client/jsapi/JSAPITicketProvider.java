package org.madtribe.wechat.core.client.jsapi;

import org.madtribe.wechat.core.client.WechatAPIClient;
import org.madtribe.wechat.core.client.accesstoken.DefaultAccessTokenProvider;
import org.madtribe.wechat.core.client.errors.WeChatResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Optional;

public class JSAPITicketProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(JSAPITicketProvider.class);

	@Inject
	private IJsAPITicketStorage jsAPIStorage;

	@Inject
	private DefaultAccessTokenProvider accessTokenProvider;

	@Inject
	private WechatAPIClient wechatAPIClient;

	private static final long EXPIRY_GRACE_PERIOD_MILLIS = 7200 * 1000;
	
	public Optional<JSAPITicket> obtainToken() {
		
		Optional<JSAPITicket> japiTicket = jsAPIStorage.currentAccessToken();
		
		if (!japiTicket.isPresent() || japiTicket.get().timeInMillisToExpiry() < EXPIRY_GRACE_PERIOD_MILLIS){
			LOGGER.info("Requesting new jsapi ticket");
			try {
				japiTicket = wechatAPIClient.requestJSAPITicket(accessTokenProvider.obtainToken());
			} catch (WeChatResponseError e) {
				LOGGER.error("Error obtaining jsapi ticket {}", e);
			}
			jsAPIStorage.storeAccessToken(japiTicket.get());
		}
		LOGGER.debug("returning access token {}", japiTicket.isPresent());
		return japiTicket;
	}

}
