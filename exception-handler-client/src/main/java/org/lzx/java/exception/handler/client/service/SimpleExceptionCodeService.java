package org.lzx.java.exception.handler.client.service;

import org.lzx.java.exception.handler.client.exception.BadFormatCodeException;
import org.lzx.java.exception.handler.client.exception.UnregisteredCodeException;
import org.lzx.java.exception.handler.client.exception.constant.CodeEnum;
import org.lzx.java.exception.handler.client.model.ExceptionCodeDetailsDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.client.RestTemplate;

/**
 * 简易使用HTTP请求异常码详情的服务
 *
 * @author LZx
 * @since 2023/1/3
 */
public class SimpleExceptionCodeService implements ExceptionCodeService {

    private final RestTemplate restTemplate;

    private final String serverApiFormat;

    public SimpleExceptionCodeService(RestTemplate restTemplate, String serverApiFormat) {
        this.restTemplate = restTemplate;
        this.serverApiFormat = serverApiFormat;
    }

    @Cacheable(
            key = "#code",
            cacheNames = CACHE_NAME,
            cacheManager = CACHE_MANAGER_NAME,
            unless = "#result == null"
    )
    @Override
    public ExceptionCodeDetailsDto query(String code) throws BadFormatCodeException, UnregisteredCodeException {
        // todo validate code format
        String serverApiUrl = String.format(serverApiFormat, code);
        ExceptionCodeDetailsDto result = restTemplate.getForObject(serverApiUrl, ExceptionCodeDetailsDto.class);
        if (result == null) {
            throw new UnregisteredCodeException(code);
        }
        return result;
    }

    @Override
    public ExceptionCodeDetailsDto defaultExceptionCode() {
        try {
            return query(CodeEnum.SOURCE_SYSTEM.code());
        } catch (BadFormatCodeException | UnregisteredCodeException e) {
            throw new RuntimeException(e);
        }
    }

}
