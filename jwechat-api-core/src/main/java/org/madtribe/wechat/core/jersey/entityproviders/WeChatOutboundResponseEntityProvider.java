package org.madtribe.wechat.core.jersey.entityproviders;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.madtribe.wechat.core.messages.inbound.response.InboundResponse;
import org.madtribe.wechat.core.streamparsers.WeChatOutboundResponseWriter;
import org.madtribe.wechat.core.wc3.messageparsers.WeChatInboundRequestW3CParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeChatOutboundResponseEntityProvider implements MessageBodyWriter<InboundResponse> {
		private static final Logger LOGGER = LoggerFactory.getLogger(WeChatOutboundResponseEntityProvider.class);
	
		@Inject
		private  WeChatOutboundResponseWriter responseWriter;
	
	    @Override
	    public long getSize(InboundResponse myBean, Class<?> type, Type genericType,
	                        Annotation[] annotations, MediaType mediaType) {
	        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
	        return -1;
	    }
	 
	    @Override
	    public void writeTo(InboundResponse myBean,
	                        Class<?> type,
	                        Type genericType,
	                        Annotation[] annotations,
	                        MediaType mediaType,
	                        MultivaluedMap<String, Object> httpHeaders,
	                        OutputStream entityStream)
	                        throws IOException, WebApplicationException {
	    	LOGGER.debug("serializing entity {} to media type {}",myBean, mediaType );
	    	
            // serialize the entity myBean to the entity output stream
        	responseWriter.writeToStream(myBean, entityStream);
        	entityStream.flush();

	    }

		@Override
	    public boolean isWriteable(Class<?> type, Type genericType,
	                               Annotation[] annotations, MediaType mediaType) {
			return InboundResponse.class.isAssignableFrom(type);
		}

}
