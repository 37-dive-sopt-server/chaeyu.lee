package org.sopt.dto.request;

import org.sopt.domain.enums.Gender;

public class MemberCreateRequestDto {
    private final String name;
    private final String birth;
    private final String email;
    private final Gender gender;

    public MemberCreateRequestDto(String name, String birth, String email, Gender gender) {
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getBirth() {
        return birth;
    }

    public String getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }
}
