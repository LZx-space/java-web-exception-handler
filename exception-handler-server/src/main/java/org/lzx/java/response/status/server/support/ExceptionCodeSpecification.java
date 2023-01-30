package org.lzx.java.response.status.server.support;

import lombok.extern.slf4j.Slf4j;
import org.lzx.java.response.status.server.model.ExceptionSourceEnum;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @author LZx
 * @since 2023/1/9
 */
@Slf4j
public class ExceptionCodeSpecification {

    /**
     * 是否符合规范
     *
     * @param exceptionCode 异常码
     * @return 符合规范则true
     */
    public static boolean isConform(String exceptionCode) {
        if (!StringUtils.hasText(exceptionCode)) {
            log.error("异常码为空");
            return false;
        }
        if (exceptionCode.length() < 5) {
            log.error("异常码长度不为5");
            return false;
        }
        String reason_source_type = exceptionCode.substring(0, 1);
        if (Arrays.stream(ExceptionSourceEnum.values()).map(Enum::name)
                .noneMatch(e -> e.equals(reason_source_type))) {
            log.error("异常码发生源标识不正确");
            return false;
        }
        String categoryId = exceptionCode.substring(1, 3);
        try {
            Integer.valueOf(categoryId);
        } catch (NumberFormatException e) {
            log.error("异常码类别ID错误");
            return false;
        }
        String sequenceNum = exceptionCode.substring(3, 5);
        try {
            Integer.valueOf(sequenceNum);
        } catch (NumberFormatException e) {
            log.error("异常码类型下的序列号错误");
            return false;
        }
        return true;
    }

}
