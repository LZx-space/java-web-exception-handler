package org.lzx.java.exception.handler.client.exception.constant;

import org.lzx.java.exception.handler.client.exception.ExceptionCode;

/**
 * @author LZx
 * @since 2023/1/3
 */
public enum CodeEnum implements ExceptionCode {

    SOURCE_CLIENT("A0000"),

    SOURCE_SYSTEM("B0000"),

    SOURCE_THIRD_PART("C0000");

    private final String code;

    CodeEnum(String code) {
        this.code = code;
    }

    @Override
    public String code() {
        return this.code;
    }

}
