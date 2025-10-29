package org.sopt.global.exception;

import org.sopt.global.exception.constant.ErrorCode;
import org.sopt.global.exception.constant.GlobalErrorCode;
import org.sopt.global.response.BaseErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<BaseErrorResponse> handlerDuplicateEmail(DuplicateEmailException ex){
        ErrorCode errorCode = ex.getErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(BaseErrorResponse.of(errorCode));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseErrorResponse> handlerInternalServerError(Exception ex) {
        return ResponseEntity.status(GlobalErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(BaseErrorResponse.of(GlobalErrorCode.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(FileOperationException.class)
    public ResponseEntity<BaseErrorResponse> handlerFileException(FileOperationException ex) {
        return ResponseEntity.status(ex.getErrorCode().getHttpStatus())
                .body(BaseErrorResponse.of(ex.getErrorCode()));
    }
}
