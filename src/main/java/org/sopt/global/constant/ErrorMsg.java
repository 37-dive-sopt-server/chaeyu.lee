package org.sopt.global.constant;

public enum ErrorMsg {
    NAME_BLANK("⚠️ 이름을 입력해주세요."),
    INVALID_GENDER("⚠️ 올바르지 않은 성별입니다. '남성' 또는 '여성'을 입력해주세요."),
    INVALID_BIRTH_FORMAT("⚠️ 생년월일은 8자리 숫자로 입력해주세요."),
    DUPLICATE_EMAIL("⚠️ 이미 등록된 이메일입니다."),
    MEMBER_NOT_FOUND(" ⚠️ 해당 ID의 회원을 찾을 수 없습니다."),
    INVALID_ID_FORMAT(" ⚠️ 유효하지 않은 ID 형식입니다. 숫자를 입력해주세요."),
    UNDER_20_CANNOT_JOIN(" ⚠️ 20세 미만의 회원은 가입이 불가능합니다.");

    private final String message;

    ErrorMsg(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
