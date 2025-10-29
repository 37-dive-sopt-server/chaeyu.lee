package org.sopt.global.exception;

import org.sopt.global.exception.constant.ErrorCode;

public class DuplicateEmailException extends RuntimeException {
    private final ErrorCode errorCode;
    public DuplicateEmailException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
