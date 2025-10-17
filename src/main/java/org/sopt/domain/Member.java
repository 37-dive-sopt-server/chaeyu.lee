package org.sopt.domain;

import org.sopt.domain.enums.Gender;

public class Member {

    private final Long id;
    private final String name;
    private final String email;
    private final String birth;
    private final Gender gender;
    private boolean isDeleted = false;

    public Member(Long id, String name, String birth, String email, Gender gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth = birth;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getBirth(){
        return birth;
    }

    public Gender getGender(){
        return gender;
    }

    public void delete(){
        this.isDeleted = true;
    }

    public boolean isDeleted(){
        return isDeleted;
    }
}