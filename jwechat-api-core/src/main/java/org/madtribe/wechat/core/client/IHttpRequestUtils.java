package org.madtribe.wechat.core.client;

import java.util.Optional;

public interface IHttpRequestUtils {

	<ENTITY_TYPE> Optional<ENTITY_TYPE> getAsObject(String url, Class<ENTITY_TYPE> class1);

}
