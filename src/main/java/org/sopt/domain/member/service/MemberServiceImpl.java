package org.sopt.domain.member.service;

import org.sopt.domain.member.domain.Member;
import org.sopt.domain.member.domain.enums.Gender;
import org.sopt.domain.member.dto.request.MemberCreateRequestDto;
import org.sopt.domain.member.dto.response.MemberResponseDto;
import org.sopt.global.exception.CustomException;
import org.sopt.global.exception.constant.GlobalErrorCode;
import org.sopt.domain.member.repository.MemberRepository;
import org.sopt.global.validator.MemberValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private long sequence;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.sequence =memberRepository.findMaxId()+1;
    }

    public Long join(MemberCreateRequestDto memberCreateRequestDto) {
        MemberValidator.validateBrith(memberCreateRequestDto.getBirth());

        if (isDuplicatedEmail(memberCreateRequestDto.getEmail())) {
            throw new CustomException(GlobalErrorCode.DUPLICATE_EMAIL);
        }

        Gender gender = MemberValidator.validateGender(memberCreateRequestDto.getGender());


        Member member = new Member(sequence++, memberCreateRequestDto.getName(), memberCreateRequestDto.getEmail(), memberCreateRequestDto.getBirth(), gender);
        memberRepository.save(member);
        return member.getId();
    }

    public Boolean isDuplicatedEmail(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    public void deleteMember(Long memberId) {
        Member member = memberRepository.findByIncludedDeleted(memberId)
                .orElseThrow(() -> new CustomException(GlobalErrorCode.MEMBER_NOT_FOUND));

        if (member.isDeleted()){
            throw new CustomException(GlobalErrorCode.ALREADY_DELETED_MEMBER);
        }

        member.delete();
        memberRepository.syncUpdate(member);
    }

    @Override
    public void close() {
        memberRepository.close();
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
