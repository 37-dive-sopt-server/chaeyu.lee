package org.sopt.controller;

import org.sopt.domain.Member;
import org.sopt.domain.enums.Gender;
import org.sopt.dto.request.MemberCreateRequestDto;
import org.sopt.dto.response.MemberResponseDto;
import org.sopt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping()
    public Long createMember(@RequestBody MemberCreateRequestDto memberCreateRequestDto) {
        return memberService.join(memberCreateRequestDto);
    }

    @GetMapping()
    public Optional<MemberResponseDto> findMemberById(Long id) {
        return memberService.findOne(id);
    }

    @GetMapping("/all")
    public List<MemberResponseDto> getAllMembers() {
        return memberService.findAllMembers();
    }

    @DeleteMapping()
    public boolean deleteMember(Long id){
        return memberService.deleteMember(id);
    }
}