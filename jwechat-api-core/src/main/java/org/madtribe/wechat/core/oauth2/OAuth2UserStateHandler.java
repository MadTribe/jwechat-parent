package org.madtribe.wechat.core.oauth2;

import java.util.Optional;

import javax.ws.rs.core.Response;

import org.madtribe.wechat.core.client.messages.OAuth2AccessToken;

public interface OAuth2UserStateHandler {

	public Response handleUserState(String state, Optional<OAuth2AccessToken> optAccessToken);

}
