package org.lzx.java.exception.handler.client.exception.constant;

import lombok.Getter;

/**
 *
 * @author LZx
 * @since 2023/1/30
 */
@Getter
public enum ExceptionSourceType {

    USER_SOURCE_CODE_INITIAL("A"),

    SYSTEM_SELF_SOURCE_CODE_INITIAL("B"),

    OTHER_SYSTEM_SOURCE_CODE_INITIAL("C");

    private final String sourceType;

    ExceptionSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

}
