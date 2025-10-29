package org.sopt.domain.member.dto.request;

import org.sopt.domain.member.domain.enums.Gender;

import static org.sopt.global.validator.MemberValidator.*;

public class MemberCreateRequestDto {
    private final String name;
    private final String birth;
    private final String email;
    private final Gender gender;

    public MemberCreateRequestDto(String name, String email, String birth, String gender) {

        validateName(name);
        validateBrithAndAge(birth);

        this.name = name;
        this.birth = birth;
        this.email = email;
        this.gender = validateGender(gender);
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
