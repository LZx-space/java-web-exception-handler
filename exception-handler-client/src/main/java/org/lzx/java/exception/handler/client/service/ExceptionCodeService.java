package org.lzx.java.exception.handler.client.service;

import org.lzx.java.exception.handler.client.exception.BadFormatCodeException;
import org.lzx.java.exception.handler.client.exception.CodedRuntimeException;
import org.lzx.java.exception.handler.client.exception.UnregisteredCodeException;
import org.lzx.java.exception.handler.client.model.ExceptionCodeDetailsDto;

/**
 * @author LZx
 * @since 2023/1/3
 */
public interface ExceptionCodeService {

    String CACHE_NAME = "exception_code_cache";

    String CACHE_MANAGER_NAME = "exception_code_cache_manager";

    /**
     * 查询注册的异常码的关联异常的详情
     *
     * @param code 异常码
     * @return 异常详情
     * @throws BadFormatCodeException    错误的异常码
     * @throws UnregisteredCodeException 未注册的异常码
     */
    ExceptionCodeDetailsDto query(String code) throws BadFormatCodeException, UnregisteredCodeException;

    /**
     * 未以{@link CodedRuntimeException}类型抛出的异常，则默认使用该异常信息
     *
     * @return 未以{@link CodedRuntimeException}抛出的异常使用的异常的详情
     */
    ExceptionCodeDetailsDto defaultExceptionCode();

}
