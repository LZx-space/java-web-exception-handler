package org.lzx.java.exception.handler.client.exception;

/**
 * @author LZx
 * @since 2023/1/4
 */
public class BadFormatCodeException extends Exception {

    public BadFormatCodeException(String badFormatCode) {
        super(badFormatCode);
    }

}
