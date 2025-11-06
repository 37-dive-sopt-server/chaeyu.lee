package org.sopt.domain.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.domain.article.domain.Article;
import org.sopt.domain.member.domain.enums.Gender;
import org.sopt.global.entity.BaseTimeEntity;
import org.sopt.global.exception.CustomException;
import org.sopt.global.exception.constant.GlobalErrorCode;

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
    @Getter
    private String name;
    @Getter
    private String email;
    @Getter
    private String birth;

    @Getter
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private boolean isDeleted = false;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Article> articles = new ArrayList<>();

    public Member(String name, String email, String birth, Gender gender) {
        validateName(name);
        validateAge(birth);

        this.name = name;
        this.birth = birth;
        this.email = email;
        this.gender = gender;
    }

    private static void validateName(String name) {
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

    public void delete() {
        this.isDeleted = true;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}