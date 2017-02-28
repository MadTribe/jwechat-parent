package org.madtribe.wechat.core.resources;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.madtribe.wechat.core.oauth2.OAuth2UserStateHandler;
import org.madtribe.wechat.core.client.WechatAPIClient;
import org.madtribe.wechat.core.client.errors.WeChatResponseError;
import org.madtribe.wechat.core.client.messages.OAuth2AccessToken;
import org.madtribe.wechat.core.configuration.WeChatConfiguration;
import org.madtribe.wechat.core.oauth2.OAuth2UserStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

@Path("/oauth2")
@Produces("application/xml;charset=UTF-8")
@Consumes({MediaType.TEXT_XML, MediaType.APPLICATION_XML})
/**
 * Wechat documentation for this here 
 * http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html
 * @author paulsmout
 *
 */
public class OAuth2EntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2EntryPoint.class);
    private static final String SNSAPI_BASE = "snsapi_base";
    private static final String STEP_2_PATH = "step2";
	private static final String STEP_1_PATH = "step1"; 
    
	@Inject
    private WeChatConfiguration weChatConfiguration;
	
	@Inject 
	private WechatAPIClient wechatAPIClient;
	
	@Inject
	private OAuth2UserStateHandler oAuth2UserStateHandler;

	/**
	 * A browser visiting this url will be redirected to the tencent oauth2 page
	 * 
	 * 
	 * @param state
	 * @return
	 * @throws URISyntaxException
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@GET
	@Path("/"+ STEP_1_PATH)
	public Response firstHop(@QueryParam("state") String state,
							@Context HttpServletRequest request) throws URISyntaxException, IOException{
		
		LOGGER.info("Request First Hop. State is {} ", state );
		
		String oAuth2URLForAppIdRedirectUriScopeAndState = weChatConfiguration.getWechatURLsConfig().getOAuth2URLForAppIdRedirectUriScopeAndStateL();

		
		
  	    String nextStepUrl = new URL(request.getRequestURL().toString()).toURI().resolve(STEP_2_PATH).toString();
		
		
		String encodedNextStepUrl = URLEncoder.encode(nextStepUrl, "UTF-8");
		
		LOGGER.info("new url " + nextStepUrl);
		
		String redirectUrl = String.format(oAuth2URLForAppIdRedirectUriScopeAndState, 
											weChatConfiguration.getWeChatAppId(),
											encodedNextStepUrl,
											SNSAPI_BASE,
											state);
		LOGGER.info("redirect url " + redirectUrl);
		try {
			return Response.seeOther(new URI(redirectUrl)).build();
		} catch (URISyntaxException e) {
			LOGGER.error("Error occured creating the redirrect url", e);
			throw e;
		} 
	}
	
	
	/**
	 * redirect_uri/?code=CODE&state=STATE。若用户禁止授权，则重定向后不会带上code参数，仅会带上state参数redirect_uri?state=STATE
	 * @param state
	 * @return
	 * @throws WeChatResponseError 
	 */
	@Path("/"+ STEP_2_PATH)
	@GET
	public Response secondHop(@QueryParam("code") String code,
							 @QueryParam("state") String state) throws WeChatResponseError{
		
		LOGGER.info("Request Second Hop. Code is {} State is {} ", code, state );
		
		Optional<OAuth2AccessToken> optAccessToken = wechatAPIClient.requestOAuth2AccessToken(code);
		
		LOGGER.info("Request Second Hop. Code is {} State is {} ", code, state );
		
		return oAuth2UserStateHandler.handleUserState(state, optAccessToken); 
	}
	
}
