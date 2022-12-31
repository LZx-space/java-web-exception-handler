package org.lzx.java.response.status.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author LZx
 * @since 2022/12/28
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ResponseStatusApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResponseStatusApplication.class, args);
    }

}
