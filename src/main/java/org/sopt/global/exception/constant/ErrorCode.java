package org.sopt.global.exception.constant;

public interface ErrorCode {
    int getHttpStatus();
    String getCode();
    String getMsg();
}
