package org.lzx.java.response.status.server.model;

/**
 * @author LZx
 * @since 2023/1/9
 */
public enum ExceptionSourceEnum {

    A("客户端请求异常"),
    B("系统自身异常"),
    C("第三方系统异常"),
    ;

    private final String source;

    ExceptionSourceEnum(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

}
