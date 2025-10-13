package org.sopt.service;

import org.sopt.domain.Member;
import org.sopt.domain.enums.Gender;
import org.sopt.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberServiceImpl implements MemberService{

    private final MemoryMemberRepository memberRepository;
    private static long sequence = 1L;

    public MemberServiceImpl(MemoryMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(String name, String birth, String email, Gender gender) {
        if (isDuplicatedEmail(email)) {
            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
        }

        Member member = new Member(sequence++, name, birth, email, gender);
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
                    return true;
                }
        ).orElse(false);
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }
}
