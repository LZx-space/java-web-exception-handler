package org.lzx.java.response.status.server.support;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author LZx
 * @since 2023/1/9
 */
class ExceptionCodeSpecificationTest {


    @Test
    void isConform() {
        assertTrue(ExceptionCodeSpecification.isConform("A0101"), "合规异常码");
    }

}