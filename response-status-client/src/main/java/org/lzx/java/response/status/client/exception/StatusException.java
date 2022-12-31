package org.lzx.java.response.status.client.exception;

/**
 * @author LZx
 * @since 2022/12/28
 */
public class StatusException extends RuntimeException {

    private final String status;

    private final String reason;

    public StatusException(String status, String reason) {
        super(reason + "-" + reason);
        this.status = status;
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }
    
}
