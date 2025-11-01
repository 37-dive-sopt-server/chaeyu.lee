package org.sopt.domain.member.service;

import org.sopt.domain.member.dto.request.MemberCreateRequestDto;
import org.sopt.domain.member.dto.response.MemberResponseDto;

import java.util.List;

public interface MemberService {

    Long join(MemberCreateRequestDto memberCreateRequestDto);
    MemberResponseDto findOne(Long memberId);
    List<MemberResponseDto> findAllMembers();
    Boolean isDuplicatedEmail(String email);
    void deleteMember(Long memberId);
    void close();
}
