package org.lzx.java.response.status.client.manager;

import org.lzx.java.response.status.client.model.ResponseStatusDto;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author LZx
 * @since 2022/12/29
 */
public class DefaultResponseStatusManager implements ResponseStatusManager {

    private final String targetUrl;

    private final RestTemplate restTemplate;

    public DefaultResponseStatusManager(String targetUrl, RestTemplate restTemplate, List<ClientHttpRequestInterceptor> requestInterceptors) {
        Assert.notNull(targetUrl, "");
        Assert.notNull(restTemplate, "");
        this.targetUrl = targetUrl;
        this.restTemplate = restTemplate;
        if (!CollectionUtils.isEmpty(requestInterceptors)) {
            this.restTemplate.getInterceptors().addAll(requestInterceptors);
        }
    }

    @Override
    public ResponseStatusDto query(String status) {
        return restTemplate.getForObject(targetUrl, ResponseStatusDto.class);
    }

}
