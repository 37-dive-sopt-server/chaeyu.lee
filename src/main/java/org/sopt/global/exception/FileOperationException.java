package org.sopt.global.exception;

import org.sopt.global.exception.constant.GlobalErrorCode;

public class FileOperationException extends RuntimeException{
    public FileOperationException(GlobalErrorCode errorCode){
        super(errorCode.getMsg());
    }
}
