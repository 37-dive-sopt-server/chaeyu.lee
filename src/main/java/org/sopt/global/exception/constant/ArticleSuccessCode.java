package org.sopt.global.exception.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter

public enum ArticleSuccessCode implements SuccessCode {

    // Article
    CREATE_ARTICLE_SUCCESS(HttpStatus.CREATED.value(), "아티클 등록 성공"),
    GET_ARTICLE_SUCCESS(HttpStatus.OK.value(), "아티클 조회 성공");

    private final int status;
    private final String msg;

    ArticleSuccessCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
