package org.lzx.java.exception.handler.client.spring.boot.starter;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.lzx.java.exception.handler.client.advice.ExceptionHandlerAdvice;
import org.lzx.java.exception.handler.client.service.DiscoveryClientExceptionCodeService;
import org.lzx.java.exception.handler.client.service.ExceptionCodeService;
import org.lzx.java.exception.handler.client.service.SimpleExceptionCodeService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.composite.CompositeDiscoveryClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * 对基于Spring MVC的项目做spring boot starter的自动配置
 *
 * @author LZx
 * @since 2023/1/3
 */
@Configuration
@AutoConfigureAfter(CompositeDiscoveryClientAutoConfiguration.class)
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@EnableCaching
@EnableConfigurationProperties(ExceptionHandlerProperties.class)
public class ExceptionHandlerAutoConfiguration {

    private final ExceptionHandlerProperties properties;

    public ExceptionHandlerAutoConfiguration(ExceptionHandlerProperties properties) {
        this.properties = properties;
    }

    @Bean
    public ExceptionHandlerAdvice exceptionHandlerAdvice(ExceptionCodeService exceptionCodeService) {
        return new ExceptionHandlerAdvice(exceptionCodeService);
    }

    @ConditionalOnMissingBean(ExceptionCodeService.class)
    @Bean
    public ExceptionCodeService exceptionCodeService() {
        return new SimpleExceptionCodeService(new RestTemplate(), properties.getSimpleClient().getServerApiFormat());
    }

    @ConditionalOnBean(DiscoveryClient.class)
    @Bean
    public ExceptionCodeService loadBalancedExceptionCodeService(DiscoveryClient discoveryClient) {
        ExceptionHandlerProperties.DiscoveryClient config = properties.getDiscoveryClient();
        return new DiscoveryClientExceptionCodeService(new RestTemplate(), discoveryClient,
                config.getServerApiUriFormat(), config.getServerServiceId());
    }

    /**
     * 配置一个缓存来减少对异常码服务端的调用，这里应该使用本地缓存，默认使用Caffeine
     *
     * @return 缓存管理器
     */
    @Bean(SimpleExceptionCodeService.CACHE_MANAGER_NAME)
    public CacheManager cacheManager() {
        ExceptionHandlerProperties.Cache cache = properties.getCache();
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCacheNames(Collections.singleton(SimpleExceptionCodeService.CACHE_NAME));
        cacheManager.setAllowNullValues(false);
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(cache.getDuration())
        );
        return cacheManager;
    }

}
