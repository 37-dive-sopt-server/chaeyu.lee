package org.sopt.controller;

import org.sopt.dto.request.MemberCreateRequestDto;
import org.sopt.dto.response.MemberResponseDto;
import org.sopt.global.exception.constant.GlobalErrorCode;
import org.sopt.global.response.BaseResponse;
import org.sopt.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BaseResponse<Long>> createMember(@RequestBody MemberCreateRequestDto memberCreateRequestDto) {
        Long id = memberService.join(memberCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.create("회원 등록 성공", id));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<BaseResponse<MemberResponseDto>> findMemberById(@PathVariable Long memberId) {
        MemberResponseDto member = memberService.findOne(memberId)
                .orElseThrow(()-> new RuntimeException(GlobalErrorCode.MEMBER_NOT_FOUND.getMsg())) ;
        return ResponseEntity.ok(BaseResponse.ok("회원 조회 성공", member));
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse<List<MemberResponseDto>>> getAllMembers() {
        List<MemberResponseDto> members = memberService.findAllMembers();
        return ResponseEntity.ok(BaseResponse.ok("전체 회원 조회 성공", members));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<BaseResponse<Boolean>> deleteMember(@PathVariable Long memberId){
        memberService.deleteMember(memberId);
        return ResponseEntity.ok(BaseResponse.ok("회원 삭제 성공", true));
    }
}