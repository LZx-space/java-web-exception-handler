package org.lzx.java.response.status.server.constant;

import lombok.Getter;

/**
 * @author LZx
 * @since 2023/1/30
 */
@Getter
public enum ExceptionCode {

    /**
     * 默认返回系统异常
     */
    DEFAULT_CODE("B0000");

    private final String code;

    ExceptionCode(String code) {
        this.code = code;
    }

}
