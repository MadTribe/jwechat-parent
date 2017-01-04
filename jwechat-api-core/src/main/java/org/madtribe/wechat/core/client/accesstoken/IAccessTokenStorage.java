package org.madtribe.wechat.core.client.accesstoken;

import java.util.Optional;

public interface IAccessTokenStorage {

	Optional<AccessToken> currentAccessToken();
	
	void storeAccessToken(AccessToken token);

}
