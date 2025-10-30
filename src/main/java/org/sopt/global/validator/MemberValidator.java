package org.sopt.global.validator;

import org.sopt.domain.member.domain.enums.Gender;
import org.sopt.domain.member.dto.request.MemberCreateRequestDto;
import org.sopt.global.exception.CustomException;
import org.sopt.global.exception.constant.GlobalErrorCode;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MemberValidator {

    public static void validateName(String name){
        if (name.trim().isEmpty()) {
            throw new CustomException(GlobalErrorCode.NAME_BLANK);
        }
    }

    public static Gender validateGender(String gender){
        return Gender.fromDisplayGender(gender);
    }

    public static void validateBrithAndAge(String birth){
        if (birth == null || birth.isBlank() || !birth.matches("\\d{8}")) {
            throw new CustomException(GlobalErrorCode.INVALID_BIRTH_FORMAT);
        }

        try{
            LocalDate birthDate = LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyyMMdd"));
            LocalDate currentDate = LocalDate.now();

            int age = Period.between(birthDate, currentDate).getYears();

            if (age<20){
                throw new CustomException(GlobalErrorCode.UNDER_20_CANNOT_JOIN);
            }
        } catch (DateTimeParseException e){
            throw new CustomException(GlobalErrorCode.INVALID_BIRTH_FORMAT);
        }
    }
}
