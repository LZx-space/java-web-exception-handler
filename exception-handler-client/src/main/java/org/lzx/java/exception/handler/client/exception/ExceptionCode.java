package org.lzx.java.exception.handler.client.exception;

/**
 * 用于指引使用枚举类型或其它自定义类型来显示定义异常码，避免使用魔法值传递
 *
 * @author LZx
 * @since 2023/1/3
 */
@FunctionalInterface
public interface ExceptionCode {

    /**
     * @return 返回异常码
     */
    String code();

}
