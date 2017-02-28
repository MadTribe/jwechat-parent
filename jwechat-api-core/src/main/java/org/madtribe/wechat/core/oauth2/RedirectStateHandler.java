package org.madtribe.wechat.core.oauth2;

import java.util.Optional;

import javax.ws.rs.core.Response;

import org.madtribe.wechat.core.client.messages.OAuth2AccessToken;

public class RedirectStateHandler implements OAuth2UserStateHandler{

	@Override
	public Response handleUserState(String state, Optional<OAuth2AccessToken> optAccessToken) {
		// TODO Auto-generated method stub
		return null;
	}
	// TODO
}
