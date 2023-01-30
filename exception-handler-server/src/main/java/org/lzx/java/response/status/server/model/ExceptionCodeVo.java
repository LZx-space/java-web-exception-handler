package org.lzx.java.response.status.server.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LZx
 * @since 2023/1/9
 */
@Data
public class ExceptionCodeVo implements Serializable {

    private String code;

    /**
     * 客户端、系统自身、第三方系统
     */
    private String sourceType;

    /**
     * 业务代码
     */
    private int businessCode;

    /**
     * 业务名称
     */
    private String business;

    /**
     * 异常原因，指定业务下异常发生的原因
     */
    private String reason;


}
