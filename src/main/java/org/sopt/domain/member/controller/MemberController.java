package org.sopt.domain.member.controller;

import jakarta.validation.Valid;
import org.sopt.domain.member.dto.request.MemberCreateRequestDto;
import org.sopt.domain.member.dto.response.MemberResponseDto;
import org.sopt.global.exception.SuccessCode.MemberSuccessCode;
import org.sopt.global.response.BaseResponse;
import org.sopt.domain.member.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping()
    public BaseResponse<Long> createMember(@Valid @RequestBody MemberCreateRequestDto memberCreateRequestDto) {
        Long id = memberService.join(memberCreateRequestDto);
        return BaseResponse.create(MemberSuccessCode.CREATE_MEMBER_SUCCESS.getMsg(), id);
    }

    @GetMapping("/{memberId}")
    public BaseResponse<MemberResponseDto> findMemberById(@PathVariable Long memberId) {
        MemberResponseDto member = memberService.findOne(memberId);
        return BaseResponse.ok(MemberSuccessCode.GET_MEMBER_SUCCESS.getMsg(), member);
    }

    @GetMapping("/all")
    public BaseResponse<List<MemberResponseDto>> getAllMembers() {
        List<MemberResponseDto> members = memberService.findAllMembers();
        return BaseResponse.ok(MemberSuccessCode.GET_ALL_MEMBERS_SUCCESS.getMsg(), members);
    }

    @DeleteMapping("/{memberId}")
    public BaseResponse<Boolean> deleteMember(@PathVariable Long memberId){
        memberService.deleteMember(memberId);
        return BaseResponse.ok(MemberSuccessCode.DELETE_MEMBER_SUCCESS.getMsg(), true);
    }
}
