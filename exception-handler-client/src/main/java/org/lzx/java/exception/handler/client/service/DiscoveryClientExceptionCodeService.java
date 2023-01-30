package org.lzx.java.exception.handler.client.service;

import org.lzx.java.exception.handler.client.exception.BadFormatCodeException;
import org.lzx.java.exception.handler.client.exception.UnregisteredCodeException;
import org.lzx.java.exception.handler.client.exception.constant.CodeEnum;
import org.lzx.java.exception.handler.client.model.ExceptionCodeDetailsDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

/**
 * 适用于服务发现的异常码客户端服务
 *
 * @author LZx
 * @since 2023/1/17
 */
public class DiscoveryClientExceptionCodeService implements ExceptionCodeService {

    private static final Random random = new Random();

    private final RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;

    private final String serverApiFormat;

    private final String targetServiceId;

    public DiscoveryClientExceptionCodeService(RestTemplate restTemplate, DiscoveryClient discoveryClient,
                                               String serverApiFormat, String targetServiceId) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
        this.serverApiFormat = serverApiFormat;
        this.targetServiceId = targetServiceId;
    }

    @Cacheable(
            key = "#code",
            cacheNames = CACHE_NAME,
            cacheManager = CACHE_MANAGER_NAME,
            unless = "#result == null"
    )
    @Override
    public ExceptionCodeDetailsDto query(String code) throws BadFormatCodeException, UnregisteredCodeException {
        ServiceInstance serviceInstance = selectInstance(targetServiceId);
        String serverApiUrl = serverApiUrl(serviceInstance, serverApiFormat, code);
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

    private ServiceInstance selectInstance(String targetServiceId) {
        List<ServiceInstance> instances = discoveryClient.getInstances(targetServiceId);
        if (CollectionUtils.isEmpty(instances)) {
            throw new RuntimeException("服务[" + targetServiceId + "]不存在");
        }
        int size = instances.size();
        int idx = random.nextInt(0, size);
        return instances.get(idx);
    }

    private String serverApiUrl(ServiceInstance serviceInstance, String serverApiFormat, String code) {
        return serviceInstance.toString() + String.format(serverApiFormat, code);
    }

}
