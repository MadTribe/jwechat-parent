package org.madtribe.wechat.core.client;

import java.io.InputStream;
import java.util.Optional;

import org.madtribe.wechat.core.client.accesstoken.AccessToken;
import org.madtribe.wechat.core.client.errors.WeChatResponseError;

public interface IHttpRequestUtils {

	<ENTITY_TYPE> Optional<ENTITY_TYPE> getAsObject(String url, Class<ENTITY_TYPE> class1) throws WeChatResponseError;

	void postObject(String url, Object object);

	<ENTITY_TYPE> Optional<ENTITY_TYPE> postFile(String url, InputStream inpustStream, String fieldName, Class<ENTITY_TYPE> class1) throws WeChatResponseError;

}
