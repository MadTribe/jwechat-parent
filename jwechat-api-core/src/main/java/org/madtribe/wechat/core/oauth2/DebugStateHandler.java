package org.madtribe.wechat.core.oauth2;

import java.util.Optional;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.madtribe.wechat.core.client.messages.OAuth2AccessToken;

public class DebugStateHandler implements OAuth2UserStateHandler {

	
	
	@Override
	public Response handleUserState(String state, Optional<OAuth2AccessToken> optAccessToken) {
		if (optAccessToken.isPresent()){
			return Response.ok("Your open id is: " + optAccessToken.get().getOpenid()).type(MediaType.TEXT_PLAIN).build();
		} else {
			return Response.ok("No access token was obtained").build();
		}
	}

}
