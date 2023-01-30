package org.lzx.java.response.status.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LZx
 * @since 2022/12/28
 */
@RestController
@SpringBootApplication
@EnableJdbcRepositories
@EnableCaching
public class ResponseStatusApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResponseStatusApplication.class, args);
    }
//
//    @GetMapping("/hello")
//    public String hello() {
//        throw new CodedRuntimeException(() -> "A0000", "中文");
//    }

}
