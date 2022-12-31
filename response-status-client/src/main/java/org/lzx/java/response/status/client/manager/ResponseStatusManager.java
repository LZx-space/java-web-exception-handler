package org.lzx.java.response.status.client.manager;

import org.lzx.java.response.status.client.model.ResponseStatusDto;

/**
 * @author LZx
 * @since 2022/12/29
 */
public interface ResponseStatusManager {

    /**
     * 查询响应码详情
     *
     * @param status 状态码
     * @return 详情
     */
    ResponseStatusDto query(String status);

}
