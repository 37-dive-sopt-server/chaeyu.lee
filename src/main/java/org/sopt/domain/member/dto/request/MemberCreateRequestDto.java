package org.sopt.domain.member.dto.request;

import org.sopt.domain.member.domain.enums.Gender;
import org.sopt.global.exception.constant.GlobalErrorCode;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MemberCreateRequestDto {
    private final String name;
    private final String birth;
    private final String email;
    private final Gender gender;

    public MemberCreateRequestDto(String name, String birth, String email, String gender) {

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

    private void validateName(String name){
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException(GlobalErrorCode.NAME_BLANK.getMsg());
        }
    }

    private Gender validateGender(String gender){
        return Gender.fromDisplayGender(gender);
    }

    private void validateBrithAndAge(String birth){
        if (!birth.matches("\\d{8}")) {
            throw new IllegalArgumentException(GlobalErrorCode.INVALID_BIRTH_FORMAT.getMsg());
        }

        try{
            LocalDate birthDate = LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyyMMdd"));
            LocalDate currentDate = LocalDate.now();

            int age = Period.between(birthDate, currentDate).getYears();

            if (age<20){
                throw new IllegalArgumentException(GlobalErrorCode.UNDER_20_CANNOT_JOIN.getMsg());
            }
        } catch (DateTimeParseException e){
            throw new IllegalArgumentException(GlobalErrorCode.INVALID_BIRTH_FORMAT.getMsg());
        }
    }
}
