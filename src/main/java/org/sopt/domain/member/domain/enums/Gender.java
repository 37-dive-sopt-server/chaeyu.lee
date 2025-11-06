package org.sopt.domain.member.domain.enums;

import lombok.Getter;
import org.sopt.global.exception.CustomException;
import org.sopt.global.exception.constant.GlobalErrorCode;

@Getter
public enum Gender {
    MALE("남성"),
    FEMALE("여성");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public static Gender fromDisplayGender(String input) {
        for (Gender gender : Gender.values()) {
            if (gender.getGender().equals(input)) {
                return gender;
            }
        }
        throw new CustomException(GlobalErrorCode.INVALID_GENDER);
    }
}
