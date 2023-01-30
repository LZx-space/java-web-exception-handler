package org.lzx.java.exception.handler.client.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 异常码详情
 *
 * @author LZx
 * @since 2022/12/28
 */
@Data
public class ExceptionCodeDetailsDto implements Serializable {

    private String code;

    private String business;

    private String reason;

}
