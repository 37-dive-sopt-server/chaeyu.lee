package org.sopt.domain.enums;

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
        throw new IllegalArgumentException("올바르지 않은 성별입니다: " + input);
    }
}
