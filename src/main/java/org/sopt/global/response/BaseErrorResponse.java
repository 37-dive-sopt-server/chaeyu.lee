package org.sopt.global.response;

import org.sopt.global.exception.constant.ErrorCode;

public class BaseErrorResponse {
    private final int status;
    private final String code;
    private final String msg;

    public BaseErrorResponse(int status, String code, String msg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
    }

    public static BaseErrorResponse of(ErrorCode errorCode) {
        return new BaseErrorResponse(
                errorCode.getHttpStatus(),
                errorCode.getCode(),
                errorCode.getMsg()
        );
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
