package org.sopt.dto.request;

import org.sopt.domain.enums.Gender;
import org.sopt.global.constant.ErrorMsg;

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
        validateBirth(birth);
        validateAge(birth);

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
        if (!birth.matches("\\d{8}")) {
            System.out.println(ErrorMsg.INVALID_BIRTH_FORMAT.getMessage());
        }
    }

    private Gender validateGender(String gender){
        return Gender.fromDisplayGender(gender);
    }

    private void validateAge(String birth){
        try{
            LocalDate birthDate = LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyyMMdd"));
            LocalDate currentDate = LocalDate.now();

            int age = Period.between(birthDate, currentDate).getYears();

            if (age<20){
                throw new IllegalArgumentException(ErrorMsg.UNDER_20_CANNOT_JOIN.getMessage());
            }
        } catch (DateTimeParseException e){
            throw new IllegalArgumentException(ErrorMsg.INVALID_BIRTH_FORMAT.getMessage());
        }
    }
}
