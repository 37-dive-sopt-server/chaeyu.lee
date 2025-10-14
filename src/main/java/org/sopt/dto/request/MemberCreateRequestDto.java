package org.sopt.dto.request;

import org.sopt.domain.enums.Gender;
import org.sopt.global.constant.ErrorMsg;

public class MemberCreateRequestDto {
    private final String name;
    private final String birth;
    private final String email;
    private final Gender gender;

    public MemberCreateRequestDto(String name, String birth, String email, String gender) {

        validateName(name);
        validateBirth(birth);

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

    private void validateName(String name){
        if (name.trim().isEmpty()) {
            System.out.println(ErrorMsg.NAME_BLANK.getMessage());
        }
    }

    private void validateBirth(String birth){
        if (!birth.matches("\\d{6}")) {
            System.out.println(ErrorMsg.INVALID_BIRTH_FORMAT.getMessage());
        }
    }

    private Gender validateGender(String gender){
        return Gender.fromDisplayGender(gender);
    }

}
