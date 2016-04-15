package org.madtribe.wechat.core.resources;


import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.hash.Hashing;
import com.google.inject.Inject;
import org.madtribe.wechat.core.configuration.WeChatConfiguration;
import org.madtribe.wechat.core.messagehandlers.WeChatInboundRequestReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.sort;

/**
 * Created by paul.smout on 02/04/2016.
 */
@Path("/wxcallback")
@Produces("application/xml;charset=UTF-8")
@Consumes({MediaType.TEXT_XML, MediaType.APPLICATION_XML})
public class WeChatEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatEntryPoint.class);

    private static final Response NOT_AUTHORIZED = Response.status(Response.Status.UNAUTHORIZED).build();
    private static final String INVALID_CONFIGURATION = "invalid";

    @Inject
    private WeChatConfiguration weChatConfiguration;

    @Inject
    private WeChatInboundRequestReader weChatMessageHandler;

    /**
     * This end point is called during the initial validation of the configuration on Wechat.
     * If the signature is correctly validated the echo string will be returned.
     *
     * @param signatureOptional
     * @param timestampOptional
     * @param nonceOptional
     * @param echoStringOptional
     * @return <echoString> or "invalid"
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String validateService(@QueryParam("signature") final Optional<String> signatureOptional,
                                  @QueryParam("timestamp") final Optional<String> timestampOptional,
                                  @QueryParam("nonce") final Optional<String> nonceOptional,
                                  @QueryParam("echostr") final Optional<String> echoStringOptional) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String response = INVALID_CONFIGURATION;
        if ( validateSignature(signatureOptional,timestampOptional,nonceOptional) ){
            response = echoStringOptional.get();
        }

        return response;
    }

    /**
     * This endpoint is called during normal message receipt.
     * @param signatureOptional
     * @param timestampOptional
     * @param nonceOptional
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @POST
    public Response onMessage(@QueryParam("signature") final Optional<String> signatureOptional,
                              @QueryParam("timestamp") final Optional<String> timestampOptional,
                              @QueryParam("nonce") final Optional<String> nonceOptional,
                              final String request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        LOGGER.info(request);
        Response response = NOT_AUTHORIZED;

        if (validateSignature(signatureOptional,timestampOptional,nonceOptional)){
            response = Response.ok().build();
            // TODO Handle This
        }

        return response;
    }

    private boolean validateSignature(@QueryParam("signature") final Optional<String> signatureOptional,
                                      @QueryParam("timestamp") final Optional<String> timestampOptional,
                                      @QueryParam("nonce") final Optional<String> nonceOptional) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        LOGGER.debug("signature = {} timestamp={} nonce={}", signatureOptional.isPresent(), timestampOptional.isPresent(), nonceOptional.isPresent());
        boolean valid = false;

        if (signatureOptional.isPresent() &&
                timestampOptional.isPresent() &&
                nonceOptional.isPresent() ) {

            String signature = signatureOptional.get();
            String timestamp = timestampOptional.get();
            String nonce = nonceOptional.get();

            LOGGER.debug("signature = {} timestamp={} nonce={} echostr={} wechatconfig ={}", signature, timestamp, nonce, weChatConfiguration.getWeChatToken());

            // Sort Parameters Alphabetically
            List<String> params = Arrays.asList(weChatConfiguration.getWeChatToken(), timestamp, nonce);
            sort(params);

            // Concatenate the result
            String concatenated = params.stream().reduce("", (a, b) -> a + b);

            // Generate SHA1 of concatenation
            String codedString = sha1OfString(concatenated);


            // Compare with signature and if the same return echo string.
            if (codedString.equals(signature)){
                valid = true;
            }
        }
        LOGGER.debug("signature valid is {}", valid);
        return valid;
    }

    private String sha1OfString(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return Hashing.sha1().hashString( input, Charsets.UTF_8 ).toString();
    }


}
