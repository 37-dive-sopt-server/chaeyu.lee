package org.sopt.global.exception.SuccessCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommentSuccessCode implements SuccessCode {
    CREATE_COMMENT_SUCCESS(HttpStatus.CREATED.value(), "댓글 등록 성공"),
    GET_COMMENT_SUCCESS(HttpStatus.OK.value(), "댓글 조회 성공"),
    DELETE_COMMENT_SUCCESS(HttpStatus.OK.value(), "댓글 삭제 성공");

    private final int status;
    private final String msg;

    CommentSuccessCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
