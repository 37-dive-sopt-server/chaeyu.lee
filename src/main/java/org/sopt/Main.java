package org.sopt;

import org.sopt.controller.MemberController;
import org.sopt.domain.Member;
import org.sopt.dto.request.MemberCreateRequestDto;
import org.sopt.global.constant.ErrorMsg;
import org.sopt.global.exception.DuplicateEmailException;
import org.sopt.global.exception.FileOperationException;
import org.sopt.repository.FileMemberRepository;
import org.sopt.repository.MemberRepository;
import org.sopt.service.MemberService;
import org.sopt.service.MemberServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}