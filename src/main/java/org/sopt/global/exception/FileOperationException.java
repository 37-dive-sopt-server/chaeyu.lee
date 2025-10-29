package org.sopt.global.exception;

import org.sopt.global.exception.constant.ErrorCode;

public class FileOperationException extends RuntimeException{
    private final ErrorCode errorCode;
    public FileOperationException(ErrorCode errorCode){
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode(){
        return errorCode;
    }
}
