package org.sopt.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.sopt.global.exception.constant.ErrorCode;
import org.sopt.global.exception.constant.GlobalErrorCode;
import org.sopt.global.response.BaseErrorResponse;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 커스텀 예외 처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseErrorResponse> handlerCustomException(CustomException ex){
        ErrorCode errorCode = ex.getErrorCode();
        log.warn("CustomException: Code: {}, Message: {}", errorCode.getCode(), errorCode.getMsg());
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(BaseErrorResponse.of(errorCode));
    }

    // 모든 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseErrorResponse> handlerInternalServerError(Exception ex) {
        log.error("Internal Server Error: ", ex);
        return ResponseEntity.status(GlobalErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(BaseErrorResponse.of(GlobalErrorCode.INTERNAL_SERVER_ERROR));
    }

    // 타입 불일치
    @ExceptionHandler(TypeMismatchException.class)
    public BaseErrorResponse handleTypeMismatch(TypeMismatchException ex) {
        log.warn("TypeMismatchException Error: ", ex);
        return BaseErrorResponse.of(GlobalErrorCode.TYPE_MISMATCH);
    }

    // JSON 파싱 실패
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseErrorResponse handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        log.warn("HttpMessageNotReadableException Error: ", ex);
        return BaseErrorResponse.of(GlobalErrorCode.INVALID_JSON);
    }
}
