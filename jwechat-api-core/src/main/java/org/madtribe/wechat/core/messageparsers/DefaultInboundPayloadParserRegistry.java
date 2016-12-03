package org.madtribe.wechat.core.messageparsers;

import java.util.Optional;

import org.madtribe.wechat.core.messageparsers.wc3.InboundPayloadParser;

public class DefaultInboundPayloadParserRegistry implements InboundPayloadParserRegistry {

	@Override
	public Optional<InboundPayloadParser> lookup(String textContent) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
