/**
 * 
 */
package org.madtribe.wechat.core.messageparsers;

import static org.madtribe.wechat.core.constants.MessageTypes.*;

import java.util.Optional;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author paulsmout
 *
 */
public class DefaultInboundPayloadParserRegistryTest {

	private DefaultInboundPayloadParserRegistry instance;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		instance = new DefaultInboundPayloadParserRegistry();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLookupTextMessageParser() {
		Optional<InboundPayloadParser> parser = instance.lookup(TEXT_MESSAGE_TYPE);
		
		assertTrue(parser.isPresent());
		
	}

}
