package org.lzx.java.response.status.server.controller;

import org.lzx.java.response.status.server.model.ExceptionCodeVo;
import org.lzx.java.response.status.server.service.ExceptionCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author LZx
 * @since 2023/1/9
 */
@RestController
@RequestMapping("/exception-codes")
public class ExceptionCodeController {

    private final ExceptionCodeService exceptionCodeService;

    public ExceptionCodeController(ExceptionCodeService exceptionCodeService) {
        this.exceptionCodeService = exceptionCodeService;
    }

    @GetMapping("{code}")
    public Optional<ExceptionCodeVo> findByCode(@PathVariable String code) {
        return exceptionCodeService.findByCode(code);
    }

}
