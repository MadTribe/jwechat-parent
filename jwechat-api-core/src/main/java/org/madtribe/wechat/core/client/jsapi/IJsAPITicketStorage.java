package org.madtribe.wechat.core.client.jsapi;

import java.util.Optional;

public interface IJsAPITicketStorage {

	Optional<JSAPITicket> currentAccessToken();

	void storeAccessToken(JSAPITicket token);

}
