package org.sopt.domain.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.domain.article.domain.Article;
import org.sopt.domain.member.domain.enums.Gender;
import org.sopt.global.entity.BaseTimeEntity;
import org.sopt.global.exception.CustomException;
import org.sopt.global.exception.ErrorCode.GlobalErrorCode;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private boolean isDeleted = false;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Article> articles = new ArrayList<>();

    private Member(String name, String email, String birth, Gender gender) {
        validateName(name);
        validateAge(birth);

        this.name = name;
        this.birth = birth;
        this.email = email;
        this.gender = gender;
    }

    public static Member create(String name, String email, String birth, Gender gender) {
        return new Member(name, email, birth, gender);
    }

    private static void validateName(String name) {
        if (name.trim().isEmpty()) {
            throw new CustomException(GlobalErrorCode.NAME_BLANK);
        }
    }

    private void validateAge(String birth) {
        LocalDate birthDate;

        try {
            birthDate = LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (Exception e) {
            throw new CustomException(GlobalErrorCode.INVALID_BIRTH_FORMAT);
        }

        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (age < 20) {
            throw new CustomException(GlobalErrorCode.UNDER_20_CANNOT_JOIN);
        }
    }

    public void delete() {
        this.isDeleted = true;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}