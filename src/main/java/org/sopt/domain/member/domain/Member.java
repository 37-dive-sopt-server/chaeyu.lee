package org.sopt.domain.member.domain;

import org.sopt.domain.member.domain.enums.Gender;
import org.sopt.global.exception.CustomException;
import org.sopt.global.exception.constant.GlobalErrorCode;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Member {

    private final Long id;
    private final String name;
    private final String email;
    private final String birth;
    private final Gender gender;
    private boolean isDeleted = false;

    public Member(Long id, String name, String email, String birth, Gender gender) {
        validateName(name);
        validateAge(birth);

        this.id = id;
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.gender = gender;
    }

    private static void validateName(String name){
        if (name.trim().isEmpty()) {
            throw new CustomException(GlobalErrorCode.NAME_BLANK);
        }
    }

    private void validateAge(String birth) {
        try {
            LocalDate birthDate = LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyyMMdd"));
            int age = Period.between(birthDate, LocalDate.now()).getYears();
            if (age < 20) {
                throw new CustomException(GlobalErrorCode.UNDER_20_CANNOT_JOIN);
            }
        } catch (Exception e) {
            throw new CustomException(GlobalErrorCode.INVALID_BIRTH_FORMAT);
        }
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getBirth(){
        return birth;
    }

    public Gender getGender(){
        return gender;
    }

    public void delete(){
        this.isDeleted = true;
    }

    public boolean isDeleted(){
        return isDeleted;
    }
}