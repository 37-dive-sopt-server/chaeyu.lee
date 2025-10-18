package org.sopt.global.exception;

import org.sopt.global.constant.ErrorMsg;

public class FileOperationException extends RuntimeException{
    public FileOperationException(ErrorMsg errorMsg){
        super(errorMsg.getMessage());
    }
}
