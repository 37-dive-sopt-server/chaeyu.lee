package org.sopt.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.sopt.domain.Member;
import org.sopt.global.constant.ErrorMsg;
import org.sopt.global.exception.FileOperationException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileMemberRepository implements MemberRepository{
    private final String FILE_PATH = "members.json";
    private final Gson gson = new Gson();
    private List<Member> store;
    private boolean isChanged = false;

    public FileMemberRepository(){
        try{
            this.store = loadMembersFromFile();
        } catch (RuntimeException e){
            this.store = new ArrayList<>();
        }
    }

    private void saveMembersToFile(){
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(store, writer);
        } catch (IOException e) {
            throw new FileOperationException(ErrorMsg.FILE_UPDATE_FAILED);
        }
    }

    private List<Member> loadMembersFromFile(){
        File file = new File(FILE_PATH);

        if (!file.exists() || file.length()==0){
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(FILE_PATH)){
            Type memberList = new TypeToken<List<Member>>() {}.getType();
            List<Member> members = gson.fromJson(reader, memberList);
            return members !=null ? members : new ArrayList<>();
        } catch (IOException e) {
            throw new FileOperationException(ErrorMsg.FILE_INIT_FAILED);
        }
    }

    @Override
    public Member save(Member member) {
        store.add(member);
        isChanged = true;
        return member;
    }

    public void close(){
        if(isChanged){
            saveMembersToFile();
            isChanged = false;
        }
    }

    public void markChanged(){
        this.isChanged = true;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return store.stream()
                .filter(member -> member.getId().equals(id))
                .filter(member -> !member.isDeleted())
                .findFirst();
    }

    @Override
    public List<Member> findAll() {
        return store.stream()
                .filter(member -> !member.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return store.stream()
                .filter(member -> member.getEmail().equals(email))
                .filter(member -> !member.isDeleted())
                .findFirst();
    }

    @Override
    public Optional<Member> findByIncludedDeleted(Long id) {
        return store.stream()
                .filter(member -> member.getId().equals(id))
                .findFirst();
    }

    @Override
    public Long findMaxId() {
        return store.stream()
                .mapToLong(Member::getId)
                .max()
                .orElse(0L);
    }
}
