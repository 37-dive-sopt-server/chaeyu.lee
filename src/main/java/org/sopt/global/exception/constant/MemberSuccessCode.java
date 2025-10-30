package org.sopt.global.exception.constant;

public enum MemberSuccessCode implements SuccessCode{
    CREATE_MEMBER_SUCCESS(201, "회원 등록 성공"),
    GET_MEMBER_SUCCESS(200, "회원 조회 성공"),
    GET_ALL_MEMBERS_SUCCESS(200, "전체 회원 조회 성공"),
    DELETE_MEMBER_SUCCESS(200, "회원 삭제 성공");

    private final int status;
    private final String message;

    MemberSuccessCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
