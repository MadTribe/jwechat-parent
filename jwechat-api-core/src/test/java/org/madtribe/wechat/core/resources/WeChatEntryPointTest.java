package org.madtribe.wechat.core.resources;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Optional;
import org.madtribe.wechat.core.configuration.WeChatConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by paul.smout on 02/04/2016.
 */
public class WeChatEntryPointTest {

    @InjectMocks
    private WeChatEntryPoint instance;

    @Mock
    private WeChatConfiguration configuration;

    private static final String token = "12345";

    @Before
    public void setup(){
        instance = new WeChatEntryPoint();
        MockitoAnnotations.initMocks(this);

        when(configuration.getWeChatToken()).thenReturn(token);



    }


    @Test
    public void testValidateService() throws Exception {

        String signature = Hashing.sha1().hashString( (token + "_nonce__timestamp_"), Charsets.UTF_8 ).toString();;

        String response = instance.validateService(Optional.of(signature),
                Optional.of("_timestamp_"),
                Optional.of("_nonce_"),
                Optional.of("_echoString_"));

        assertThat(response,equalTo("_echoString_"));
    }


    @Test
    public void testValidateServiceRealData() throws Exception {

        String signature = "f1127df2578cfdeaed66a833d770ef7a03d19cae";

        String response = instance.validateService(Optional.of(signature),
                Optional.of("1460425826"),
                Optional.of("1484736675"),
                Optional.of("4774662673490968303"));

        assertThat(response,equalTo("4774662673490968303"));
    }

    @Test
    public void testValidateServiceInvalidSignature() throws Exception {

        String response = instance.validateService(Optional.of("invalid_signature"),
                Optional.of("_timestamp_"),
                Optional.of("_nonce_"),
                Optional.of("_echoString_"));

        assertThat(response,equalTo("invalid"));
    }


    private String sha1OfString(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.update(input.getBytes());
        byte[] codedBytes = messageDigest.digest();
        input = new String(codedBytes, "UTF-8");

        return input;
    }
}
