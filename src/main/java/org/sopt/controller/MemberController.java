package org.sopt.controller;

import org.sopt.domain.Member;
import org.sopt.domain.enums.Gender;
import org.sopt.service.MemberServiceImpl;

import java.util.List;
import java.util.Optional;

public class MemberController {

    private final MemberServiceImpl memberService = new MemberServiceImpl();

    public Long createMember(String name, String birth, String email, Gender gender) {

        return memberService.join(name, birth, email, gender);
    }

    public Optional<Member> findMemberById(Long id) {
        return memberService.findOne(id);
    }

    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }
}