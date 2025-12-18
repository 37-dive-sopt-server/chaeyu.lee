package org.sopt.global.exception.ErrorCode;

public interface ErrorCode {
    int getHttpStatus();
    String getCode();
    String getMsg();
}
