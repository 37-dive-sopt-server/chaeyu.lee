package org.sopt.global.response;

import org.sopt.global.exception.ErrorCode.ErrorCode;

public record BaseErrorResponse(int status, String code, String msg) {

    public static BaseErrorResponse of(ErrorCode errorCode) {
        return new BaseErrorResponse(
                errorCode.getHttpStatus(),
                errorCode.getCode(),
                errorCode.getMsg()
        );
    }
}
