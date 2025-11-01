package org.sopt.domain.member.dto.response;

import org.sopt.domain.member.domain.Member;
import org.sopt.domain.member.domain.enums.Gender;

public class MemberResponseDto {
    private final Long id;
    private final String name;
    private final String birth;
    private final String email;
    private final Gender gender;

    public MemberResponseDto(Long id, String name, String birth, String email, Gender gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth = birth;
        this.gender = gender;
    }


    public static MemberResponseDto from (Member member){
        return new MemberResponseDto(
                member.getId(),
                member.getName(),
                member.getBirth(),
                member.getEmail(),
                member.getGender());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBirth() {
        return birth;
    }

    public Gender getGender() {
        return gender;
    }

}
