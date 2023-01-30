package org.lzx.java.exception.handler.client.spring.boot.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * @author LZx
 * @since 2023/1/3
 */
@Data
@ConfigurationProperties("exception.handler.client")
public class ExceptionHandlerProperties {

    private SimpleClient simpleClient = new SimpleClient();

    private DiscoveryClient discoveryClient = new DiscoveryClient();

    private Cache cache = new Cache();

    @Data
    public static class Cache {

        private Duration duration = Duration.ofMinutes(30);

    }

    @Data
    public static class SimpleClient {

        private String serverApiFormat;

    }

    @Data
    public static class DiscoveryClient {

        private String serverApiUriFormat;

        private String serverServiceId;

    }

}
