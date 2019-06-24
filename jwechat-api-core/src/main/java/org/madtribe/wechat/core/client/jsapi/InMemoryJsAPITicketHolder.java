package org.madtribe.wechat.core.client.jsapi;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class InMemoryJsAPITicketHolder implements IJsAPITicketStorage {



	private Optional<JSAPITicket> accessToken = Optional.empty();


	@Override
	public Optional<JSAPITicket> currentAccessToken() {
		return accessToken;
	}

	@Override
	public void storeAccessToken(JSAPITicket token) {
		accessToken = Optional.of(token);
	}
	

}
