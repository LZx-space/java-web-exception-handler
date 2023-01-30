package org.lzx.java.exception.handler.client.exception;

import org.lzx.java.exception.handler.client.exception.constant.ExceptionHttpStatus;
import org.lzx.java.exception.handler.client.exception.constant.ExceptionSourceType;

import java.io.Serializable;

/**
 * @author LZx
 * @since 2022/12/28
 */
public class CodedRuntimeException extends RuntimeException implements ExceptionCode {

    private final String code;

    private final Serializable data;

    public CodedRuntimeException(ExceptionCode exceptionCode) {
        super("[" + exceptionCode.code() + "]", null, false, false);
        this.code = exceptionCode.code();
        this.data = null;
    }

    public CodedRuntimeException(ExceptionCode exceptionCode, String message) {
        super("[" + exceptionCode.code() + "]-" + message, null, false, false);
        this.code = exceptionCode.code();
        this.data = null;
    }

    public CodedRuntimeException(ExceptionCode exceptionCode, String message, Serializable data) {
        super("[" + exceptionCode.code() + "]-" + message, null, false, false);
        this.code = exceptionCode.code();
        this.data = data;
    }

    @Override
    public String code() {
        return this.code;
    }

    public Serializable getData() {
        return this.data;
    }

    public int httpStatus() {
        if (code.startsWith(ExceptionSourceType.USER_SOURCE_CODE_INITIAL.getSourceType())) {
            return ExceptionHttpStatus.USER_REASON_HTTP_STATUS.getStatus();
        }
        if (code.startsWith(ExceptionSourceType.SYSTEM_SELF_SOURCE_CODE_INITIAL.getSourceType())) {
            return ExceptionHttpStatus.SYSTEM_SELF_REASON_HTTP_STATUS.getStatus();
        }
        if (code.startsWith(ExceptionSourceType.OTHER_SYSTEM_SOURCE_CODE_INITIAL.getSourceType())) {
            return ExceptionHttpStatus.OTHER_SYSTEM_REASON_HTTP_STATUS.getStatus();
        }
        throw new IllegalArgumentException("异常码格式错误-[" + code + "]");
    }

}
