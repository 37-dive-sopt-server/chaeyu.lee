package org.sopt.service;

import org.sopt.domain.Member;
import org.sopt.dto.request.MemberCreateRequestDto;
import org.sopt.global.constant.ErrorMsg;
import org.sopt.global.exception.DuplicateEmailException;
import org.sopt.repository.FileMemberRepository;
import org.sopt.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberRepository memberRepository;
    private long sequence;

    public Long join(MemberCreateRequestDto memberCreateRequestDto) {
        if (isDuplicatedEmail(memberCreateRequestDto.getEmail())) {
            throw new DuplicateEmailException(ErrorMsg.DUPLICATE_EMAIL);
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

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }
}
