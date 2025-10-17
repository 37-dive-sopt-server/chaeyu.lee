package org.sopt.service;

import org.sopt.domain.Member;
import org.sopt.domain.enums.Gender;
import org.sopt.dto.request.MemberCreateRequestDto;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    Long join(MemberCreateRequestDto memberCreateRequestDto);
    Optional<Member> findOne(Long memberId);
    List<Member> findAllMembers();
    Boolean isDuplicatedEmail(String email);
    Boolean deleteMember(Long memberId);
    void close();
}
