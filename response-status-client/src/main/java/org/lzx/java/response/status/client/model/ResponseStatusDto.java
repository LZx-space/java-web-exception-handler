package org.lzx.java.response.status.client.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LZx
 * @since 2022/12/28
 */
@Data
public class ResponseStatusDto implements Serializable {

    private String status;

    private String reason;


}
