package org.madtribe.wechat.core.messageparsers;

/**
 * Created by paul.smout on 15/04/2016.
 */
public class MessageParsingException extends Exception{
    public MessageParsingException() {
    }

    public MessageParsingException(String message) {
        super(message);
    }

    public MessageParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageParsingException(Throwable cause) {
        super(cause);
    }
}
