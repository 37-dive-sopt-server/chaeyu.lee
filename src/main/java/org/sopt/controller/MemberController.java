package org.sopt.controller;

import org.sopt.domain.Member;
import org.sopt.domain.enums.Gender;
import org.sopt.service.MemberServiceImpl;

import java.util.List;
import java.util.Optional;

public class MemberController {

    private final MemberServiceImpl memberService;

    public MemberController(MemberServiceImpl memberService) {
        this.memberService = memberService;
    }

    public Long createMember(String name, String birth, String email, Gender gender) {

        return memberService.join(name, birth, email, gender);
    }

    public Optional<Member> findMemberById(Long id) {
        return memberService.findOne(id);
    }

    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }

    public boolean isDuplicatedEmail(String email) {
        return memberService.isDuplicatedEmail(email);
    }

    public boolean deleteMember(Long id){
        return memberService.deleteMember(id);
    }
}