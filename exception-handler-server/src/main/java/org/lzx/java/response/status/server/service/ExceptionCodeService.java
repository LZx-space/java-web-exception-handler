package org.lzx.java.response.status.server.service;

import org.lzx.java.response.status.server.model.ExceptionCodeVo;

import java.util.Optional;

/**
 * @author LZx
 * @since 2023/1/9
 */
public interface ExceptionCodeService {

    Optional<ExceptionCodeVo> findByCode(String exceptionCode);

}
