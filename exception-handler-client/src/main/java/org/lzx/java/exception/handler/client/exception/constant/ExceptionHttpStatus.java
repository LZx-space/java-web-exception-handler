package org.lzx.java.exception.handler.client.exception.constant;

import lombok.Getter;

/**
 * 不同异常源返回HTTP请求时使用的HTTP状态码
 *
 * @author LZx
 * @since 2023/1/30
 */
@Getter
public enum ExceptionHttpStatus {

    USER_REASON_HTTP_STATUS(499),

    SYSTEM_SELF_REASON_HTTP_STATUS(598),

    OTHER_SYSTEM_REASON_HTTP_STATUS(599);

    private final int status;

    ExceptionHttpStatus(int status) {
        this.status = status;
    }

}
