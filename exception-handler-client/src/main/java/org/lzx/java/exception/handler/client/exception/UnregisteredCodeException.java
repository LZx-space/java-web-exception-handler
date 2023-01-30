package org.lzx.java.exception.handler.client.exception;

/**
 * @author LZx
 * @since 2023/1/4
 */
public class UnregisteredCodeException extends Exception {

    /**
     * @param unregisteredExceptionCode 未注册的异常码
     */
    public UnregisteredCodeException(String unregisteredExceptionCode) {
        super(unregisteredExceptionCode);
    }

}
