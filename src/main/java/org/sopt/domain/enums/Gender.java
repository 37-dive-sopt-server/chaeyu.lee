package org.sopt.domain.enums;

import org.sopt.global.constant.ErrorMsg;

public enum Gender {
    MALE("남성"),
    FEMALE("여성");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public static Gender fromDisplayGender(String input) {
        for (Gender gender : Gender.values()) {
            if (gender.getGender().equals(input)) {
                return gender;
            }
        }
        throw new IllegalArgumentException(ErrorMsg.INVALID_GENDER.getMessage());
    }
}
