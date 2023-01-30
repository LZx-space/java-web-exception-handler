package org.lzx.java.exception.handler.client.model;

import lombok.Data;

/**
 * @author LZx
 * @since 2023/1/3
 */
@Data
public class ExceptionResponse<T> {

    private String code;

    private String message;

    private T data;

    public ExceptionResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ExceptionResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
