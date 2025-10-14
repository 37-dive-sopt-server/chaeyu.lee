package org.sopt;

import org.sopt.controller.MemberController;
import org.sopt.domain.Member;
import org.sopt.dto.request.MemberCreateRequestDto;
import org.sopt.global.constant.ErrorMsg;
import org.sopt.global.exception.DuplicateEmailException;
import org.sopt.repository.FileMemberRepository;
import org.sopt.repository.MemberRepository;
import org.sopt.service.MemberService;
import org.sopt.service.MemberServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // MemberRepository memberRepository = new MemoryMemberRepository();
        MemberRepository memberRepository = new FileMemberRepository();
        MemberService memberService = new MemberServiceImpl(memberRepository);
        MemberController memberController = new MemberController(memberService);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n✨ --- DIVE SOPT 회원 관리 서비스 --- ✨");
            System.out.println("---------------------------------");
            System.out.println("1️⃣. 회원 등록 ➕");
            System.out.println("2️⃣. ID로 회원 조회 🔍");
            System.out.println("3️⃣. 전체 회원 조회 📋");
            System.out.println("4️⃣. 회원 삭제 ❌");
            System.out.println("5️⃣. 종료 🚪");
            System.out.println("---------------------------------");
            System.out.print("메뉴를 선택하세요: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("등록할 회원 이름을 입력하세요: ");
                    String name = scanner.nextLine();

                    System.out.print("생년월일을 입력하세요 (생년월일 8자리 yyyymmdd): ");
                    String birth = scanner.nextLine();

                    System.out.print("성별을 입력하세요 (남성/여성) : ");
                    String gender = scanner.nextLine();

                    System.out.print("이메일을 입력하세요: ");
                    String email = scanner.nextLine();

                    try {
                        MemberCreateRequestDto memberCreateRequestDto = new MemberCreateRequestDto(name, birth, email, gender);
                        Long createdId = memberController.createMember(memberCreateRequestDto);
                        System.out.println("✅ 회원 등록 완료 (ID: " + createdId + ")");
                    } catch (IllegalArgumentException | DuplicateEmailException e) {
                        System.out.println("❌ 회원 등록 실패: " + e.getMessage());
                    }
                    break;

                case "2":
                    System.out.print("조회할 회원 ID를 입력하세요: ");
                    try {
                        Long id = Long.parseLong(scanner.nextLine());
                        Optional<Member> foundMember = memberController.findMemberById(id);
                        if (foundMember.isPresent()) {
                            System.out.println("✅ 조회된 회원: ID=" + foundMember.get().getId() + ", 이름="
                                    + foundMember.get().getName() + ", 생년월일=" + foundMember.get().getBirth()
                                    + ", 이메일=" + foundMember.get().getEmail()
                                    + ", 성별=" + foundMember.get().getGender());
                        } else {
                            System.out.println(ErrorMsg.MEMBER_NOT_FOUND.getMessage());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(ErrorMsg.INVALID_ID_FORMAT.getMessage());
                    }
                    break;
                case "3":
                    List<Member> allMembers = memberController.getAllMembers();
                    if (allMembers.isEmpty()) {
                        System.out.println("ℹ️ 등록된 회원이 없습니다.");
                    }
                    else {
                        System.out.println("--- 📋 전체 회원 목록 📋 ---");
                        for (Member member : allMembers) {
                            System.out.println("👤 ID=" + member.getId() + ", 이름=" + member.getName());
                        }
                        System.out.println("--------------------------");
                    }
                    break;
                case "4":
                    System.out.print("삭제할 회원 ID를 입력하세요: ");
                    try {
                        Long id = Long.parseLong(scanner.nextLine());
                        boolean success = memberController.deleteMember(id);
                        if (success) {
                            System.out.println("✅ 해당 ID의 회원을 삭제하였습니다.");
                        } else {
                            System.out.println(ErrorMsg.MEMBER_NOT_FOUND.getMessage());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(ErrorMsg.INVALID_ID_FORMAT.getMessage());
                    }
                    break;

                case "5":
                    memberRepository.close();
                    System.out.println("👋 서비스를 종료합니다. 안녕히 계세요!");
                    scanner.close();
                    return;
                default:
                    System.out.println("🚫 잘못된 메뉴 선택입니다. 다시 시도해주세요.");
            }
        }
    }
}