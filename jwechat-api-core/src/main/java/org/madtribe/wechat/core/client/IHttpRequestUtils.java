package org.madtribe.wechat.core.client;

import java.util.Optional;

import org.madtribe.wechat.core.client.accesstoken.AccessToken;

public interface IHttpRequestUtils {

	<ENTITY_TYPE> Optional<ENTITY_TYPE> getAsObject(String url, Class<ENTITY_TYPE> class1);


	void postObject(String url, Object object);

}
