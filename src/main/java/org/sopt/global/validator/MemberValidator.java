package org.sopt.global.validator;

import org.sopt.domain.member.domain.enums.Gender;
import org.sopt.global.exception.CustomException;
import org.sopt.global.exception.constant.GlobalErrorCode;

public class MemberValidator {

    public static Gender validateGender(String gender){
        return Gender.fromDisplayGender(gender);
    }

    public static void validateBirth(String birth){
        if (birth == null || birth.isBlank() || !birth.matches("\\d{8}")) {
            throw new CustomException(GlobalErrorCode.INVALID_BIRTH_FORMAT);
        }
    }
}
