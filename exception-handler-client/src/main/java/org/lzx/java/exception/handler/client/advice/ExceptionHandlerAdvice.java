package org.lzx.java.exception.handler.client.advice;

import lombok.extern.slf4j.Slf4j;
import org.lzx.java.exception.handler.client.annotation.CodedExceptionHandler;
import org.lzx.java.exception.handler.client.exception.BadFormatCodeException;
import org.lzx.java.exception.handler.client.exception.CodedRuntimeException;
import org.lzx.java.exception.handler.client.exception.UnregisteredCodeException;
import org.lzx.java.exception.handler.client.exception.constant.ExceptionHttpStatus;
import org.lzx.java.exception.handler.client.model.ExceptionCodeDetailsDto;
import org.lzx.java.exception.handler.client.model.ExceptionResponse;
import org.lzx.java.exception.handler.client.service.ExceptionCodeService;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author LZx
 * @since 2023/1/3
 */
@Slf4j
@ControllerAdvice(annotations = CodedExceptionHandler.class)
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    private final ExceptionCodeService exceptionCodeService;

    public ExceptionHandlerAdvice(ExceptionCodeService exceptionCodeService) {
        this.exceptionCodeService = exceptionCodeService;
    }

    @ResponseBody
    @ResponseStatus
    @ExceptionHandler(CodedRuntimeException.class)
    public ResponseEntity<?> handle(CodedRuntimeException e) {
        try {
            ExceptionCodeDetailsDto detailsDto = exceptionCodeService.query(e.code());
            int httpStatus = e.httpStatus();
            String message = detailsDto.getBusiness() + "-" + detailsDto.getReason();
            log.error("请求异常-异常码[{}]-描述[{}]", e.code(), message);
            return ResponseEntity.status(httpStatus)
                    .cacheControl(CacheControl.noCache())
                    .body(new ExceptionResponse<>(detailsDto.getCode(), message, e.getData()));
        } catch (BadFormatCodeException ex) {
            log.error("请求异常-异常码[{}]-格式错误", e.code());
            return ResponseEntity.badRequest()
                    .cacheControl(CacheControl.noCache())
                    .body("异常码[" + e.code() + "]格式错误");
        } catch (UnregisteredCodeException ex) {
            log.error("请求异常-异常码[{}]-未注册", e.code());
            return ResponseEntity.badRequest()
                    .cacheControl(CacheControl.noCache())
                    .body("异常码[" + e.code() + "]未注册");
        }
    }

    @ResponseBody
    @ResponseStatus
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> defaultHandle(Exception e) {
        ExceptionCodeDetailsDto detailsDto = exceptionCodeService.defaultExceptionCode();
        String message = detailsDto.getBusiness() + "-" + detailsDto.getReason();
        log.error("请求异常-未分配异常码的异常-异常信息[{}]", e.getMessage());
        return ResponseEntity.status(ExceptionHttpStatus.SYSTEM_SELF_REASON_HTTP_STATUS.getStatus())
                .cacheControl(CacheControl.noCache())
                .body(new ExceptionResponse<>(detailsDto.getCode(), message, null));
    }

}
