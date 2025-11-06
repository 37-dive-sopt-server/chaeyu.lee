package org.sopt.global.exception.constant;

import org.springframework.http.HttpStatus;

public enum GlobalErrorCode implements ErrorCode {

    // Member
    NAME_BLANK(HttpStatus.BAD_REQUEST.value(), "NAME_BLANK", "이름을 입력해주세요."),
    INVALID_GENDER(HttpStatus.BAD_REQUEST.value(), "INVALID_GENDER", "올바르지 않은 성별입니다. '남성' 또는 '여성'을 입력해주세요."),
    INVALID_BIRTH_FORMAT(HttpStatus.BAD_REQUEST.value(), "INVALID_BIRTH_FORMAT", "생년월일은 8자리 숫자로 입력해주세요."),
    ALREADY_DELETED_MEMBER(HttpStatus.BAD_REQUEST.value(), "ALREADY_DELETED_MEMBER", "이미 삭제된 회원의 ID 입니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT.value(), "DUPLICATE_EMAIL", "이미 등록된 이메일입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "MEMBER_NOT_FOUND", "해당 ID의 회원을 찾을 수 없습니다."),
    INVALID_ID_FORMAT(HttpStatus.BAD_REQUEST.value(), "INVALID_ID_FORMAT", "유효하지 않은 ID 형식입니다. 숫자를 입력해주세요."),
    UNDER_20_CANNOT_JOIN(HttpStatus.FORBIDDEN.value(), "UNDER_20_CANNOT_JOIN", "20세 미만의 회원은 가입이 불가능합니다."),

    // Article
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ARTICLE_NOT_FOUND", "해당 ID의 아티클을 찾을 수 없습니다."),
    DUPLICATE_ARTICLE_TITLE(HttpStatus.BAD_REQUEST.value(), "DUPLICATE_ARTICLE_TITLE", "이미 등록된 아티클 제목입니다."),

    // Global
    FILE_INIT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR.value(), "FILE_INIT_FAILED", "파일 초기화를 실패했습니다."),
    FILE_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR.value(), "FILE_UPDATE_FAILED", "데이터 파일 저장에 실패하였습니다."),
    INVALID_JSON(HttpStatus.BAD_REQUEST.value(), "INVALID_JSON", "잘못된 JSON 형식입니다."),
    TYPE_MISMATCH(HttpStatus.BAD_REQUEST.value(), "TYPE_MISMATCH", "요청 타입이 올바르지 않습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생했습니다.");

    private final int httpStatus;
    private final String code;
    private final String msg;


    GlobalErrorCode(int httpStatus, String code, String msg) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getCode() {
        return code;
    }
}
