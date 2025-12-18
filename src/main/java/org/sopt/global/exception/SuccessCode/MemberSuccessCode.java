package org.sopt.global.exception.SuccessCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MemberSuccessCode implements SuccessCode{
    // Member
    CREATE_MEMBER_SUCCESS(HttpStatus.CREATED.value(), "회원 등록 성공"),
    GET_MEMBER_SUCCESS(HttpStatus.OK.value(), "회원 조회 성공"),
    GET_ALL_MEMBERS_SUCCESS(HttpStatus.OK.value(), "전체 회원 조회 성공"),
    DELETE_MEMBER_SUCCESS(HttpStatus.OK.value(), "회원 삭제 성공");

    private final int status;
    private final String msg;

    MemberSuccessCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

}
