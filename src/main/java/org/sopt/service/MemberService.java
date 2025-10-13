package org.sopt.service;

import org.sopt.domain.Member;
import org.sopt.domain.enums.Gender;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    Long join(String name, String birth, String email, Gender gender);
    Optional<Member> findOne(Long memberId);
    List<Member> findAllMembers();
}
