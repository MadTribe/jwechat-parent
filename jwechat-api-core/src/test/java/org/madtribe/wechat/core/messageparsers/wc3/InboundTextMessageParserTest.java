package org.madtribe.wechat.core.messageparsers.wc3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.madtribe.wechat.core.wc3.messageparsers.AbstractPayloadParser;
import org.madtribe.wechat.core.wc3.messageparsers.InboundTextMessageParser;

public class InboundTextMessageParserTest {
	
	private AbstractPayloadParser instance;

	@Before
	public void setUp() throws Exception {
		instance = new InboundTextMessageParser();
	}

	@After
	public void tearDown() throws Exception {
	}

}
