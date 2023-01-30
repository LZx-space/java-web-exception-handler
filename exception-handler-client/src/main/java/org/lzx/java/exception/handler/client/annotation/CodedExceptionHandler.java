package org.lzx.java.exception.handler.client.annotation;

import org.lzx.java.exception.handler.client.advice.ExceptionHandlerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

/**
 * 使用该注解则会为{@link RequestMapping}的方法添加{@link ExceptionHandlerAdvice}异常处理
 *
 * @author LZx
 * @since 2022/12/28
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CodedExceptionHandler {
}
