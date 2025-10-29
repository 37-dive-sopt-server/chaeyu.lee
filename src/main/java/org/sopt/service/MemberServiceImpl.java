package org.sopt.service;

import org.sopt.domain.Member;
import org.sopt.dto.request.MemberCreateRequestDto;
import org.sopt.dto.response.MemberResponseDto;
import org.sopt.global.exception.DuplicateEmailException;
import org.sopt.global.exception.constant.GlobalErrorCode;
import org.sopt.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        if (isDuplicatedEmail(memberCreateRequestDto.getEmail())) {
            throw new DuplicateEmailException(GlobalErrorCode.DUPLICATE_EMAIL);
        }

        Member member = new Member(sequence++, memberCreateRequestDto.getName(), memberCreateRequestDto.getBirth(), memberCreateRequestDto.getEmail(), memberCreateRequestDto.getGender());
        memberRepository.save(member);
        return member.getId();
    }

    public Boolean isDuplicatedEmail(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    public Boolean deleteMember(Long memberId) {
        return memberRepository.findByIncludedDeleted(memberId).map(
                member -> {
                    if (member.isDeleted()){
                        return false;
                    }

                    member.delete();
                    memberRepository.syncUpdate(member);

                    return true;
                }
        ).orElse(false);
    }

    @Override
    public void close() {
        memberRepository.close();
    }

    public Optional<MemberResponseDto> findOne(Long memberId) {
        return memberRepository.findById(memberId)
                .map(MemberResponseDto::from);
    }

    public List<MemberResponseDto> findAllMembers() {
        return memberRepository.findAll()
                .stream().map(MemberResponseDto::from)
                .collect(Collectors.toList());
    }
}
