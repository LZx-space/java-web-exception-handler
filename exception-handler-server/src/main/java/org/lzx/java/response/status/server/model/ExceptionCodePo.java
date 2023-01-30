package org.lzx.java.response.status.server.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author LZx
 * @since 2023/1/8
 */
@Data
@Table("exception_code")
public class ExceptionCodePo {

    @Id
    private String code;

    /**
     * A-客户原因、B-系统原因、C-第三方原因
     */
    private ExceptionSourceEnum sourceType;

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
