package org.sopt.repository;

import org.sopt.domain.Member;

import java.util.*;
import java.util.stream.Collectors;

public class MemoryMemberRepository implements MemberRepository{


    private static final Map<Long, Member> store = new HashMap<>();


    public Member save(Member member) {
        store.put(member.getId(), member);
        return member;

    }


    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id))
                .filter(member -> !member.isDeleted());
    }


    public List<Member> findAll() {
        return store.values().stream()
                .filter(member -> !member.isDeleted())
                .collect(Collectors.toList());
    }

    public Optional<Member> findByEmail(String email){
        return store.values().stream()
                .filter(member -> member.getEmail().equals(email))
                .filter(member -> !member.isDeleted())
                .findFirst();
    }

    public Optional<Member> findByIncludedDeleted(Long id){
        return Optional.ofNullable(store.get(id));
    }

    public Long findMaxId() {
        return store.keySet().stream()
                .mapToLong(Long::longValue)
                .max()
                .orElse(0L);
    }

    @Override
    public void close() {}

    @Override
    public void syncUpdate(Member member) {}
}
