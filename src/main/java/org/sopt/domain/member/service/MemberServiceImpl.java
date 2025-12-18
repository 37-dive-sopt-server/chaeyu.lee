package org.sopt.domain.member.service;

import org.sopt.domain.member.domain.Member;
import org.sopt.domain.member.domain.enums.Gender;
import org.sopt.domain.member.dto.request.MemberCreateRequestDto;
import org.sopt.domain.member.dto.response.MemberResponseDto;
import org.sopt.global.exception.CustomException;
import org.sopt.global.exception.constant.GlobalErrorCode;
import org.sopt.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(MemberCreateRequestDto memberCreateRequestDto) {

        if (isDuplicatedEmail(memberCreateRequestDto.getEmail())) {
            throw new CustomException(GlobalErrorCode.DUPLICATE_EMAIL);
        }

        Gender gender = Gender.fromDisplayGender(memberCreateRequestDto.getGender());

        Member member = Member.create(
                memberCreateRequestDto.getName(),
                memberCreateRequestDto.getEmail(),
                memberCreateRequestDto.getBirth(),
                gender
        );
        memberRepository.save(member);

        return member.getId();
    }

    public Boolean isDuplicatedEmail(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    public void deleteMember(Long memberId) {
        Member member = memberRepository.findByIncludedDeleted(memberId)
                .orElseThrow(() -> new CustomException(GlobalErrorCode.MEMBER_NOT_FOUND));

        if (member.isDeleted()) {
            throw new CustomException(GlobalErrorCode.ALREADY_DELETED_MEMBER);
        }

        member.delete();
    }

    public MemberResponseDto findOne(Long memberId) {
        return memberRepository.findById(memberId)
                .map(MemberResponseDto::from)
                .orElseThrow(() -> new CustomException(GlobalErrorCode.MEMBER_NOT_FOUND));
    }

    public List<MemberResponseDto> findAllMembers() {
        return memberRepository.findAll()
                .stream().map(MemberResponseDto::from)
                .collect(Collectors.toList());
    }
}
