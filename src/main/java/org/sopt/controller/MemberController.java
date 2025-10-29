package org.sopt.controller;

import org.sopt.domain.Member;
import org.sopt.domain.enums.Gender;
import org.sopt.dto.request.MemberCreateRequestDto;
import org.sopt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {

    @Autowired
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/users")
    public Long createMember(MemberCreateRequestDto memberCreateRequestDto) {

        return memberService.join(memberCreateRequestDto);
    }

    @GetMapping("/users")
    public Optional<Member> findMemberById(Long id) {
        return memberService.findOne(id);
    }

    @GetMapping("/users/all")
    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }

    @DeleteMapping("/users")
    public boolean deleteMember(Long id){
        return memberService.deleteMember(id);
    }
}