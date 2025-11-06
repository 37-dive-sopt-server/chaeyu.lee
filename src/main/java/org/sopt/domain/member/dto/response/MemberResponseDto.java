package org.sopt.domain.member.dto.response;

import org.sopt.domain.member.domain.Member;
import org.sopt.domain.member.domain.enums.Gender;

public record MemberResponseDto(Long id, String name, String birth, String email, Gender gender) {

    public static MemberResponseDto from(Member member) {
        return new MemberResponseDto(
                member.getId(),
                member.getName(),
                member.getBirth(),
                member.getEmail(),
                member.getGender());
    }

}
