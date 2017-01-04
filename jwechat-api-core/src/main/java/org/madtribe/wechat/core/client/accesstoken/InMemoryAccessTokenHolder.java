package org.madtribe.wechat.core.client.accesstoken;

import java.util.Optional;

import javax.inject.Singleton;

@Singleton
public class InMemoryAccessTokenHolder implements IAccessTokenStorage{

	private Optional<AccessToken> accessToken = Optional.empty();

	@Override
	public Optional<AccessToken> currentAccessToken() {

		return accessToken;
	}

	@Override
	public void storeAccessToken(AccessToken token) {
		accessToken = Optional.of(token);
	}
	

}
