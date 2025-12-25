package org.sopt.domain.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sopt.domain.member.domain.enums.Gender;
import org.sopt.global.exception.CustomException;
import org.sopt.global.exception.ErrorCode.GlobalErrorCode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {

    @Test
    @DisplayName("Member 엔티티 생성 테스트")
    void createMember() {
        // given
        String name = "복복복";
        String email = "test@test.com";
        String birth = "20000101";

        // when
        Member member = Member.create(name, email, birth, Gender.FEMALE);

        // then
        assertThat(member.getName()).isEqualTo(name);
        assertThat(member.getBirth()).isEqualTo(birth);
        assertThat(member.getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("이름이 공백일 경우 회원가입에 실패한다")
    void failCreateMemberWithBlank() {
        assertThatThrownBy(() -> Member.create(" ", "test@test.com", "20000101", Gender.FEMALE))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("errorCode", GlobalErrorCode.NAME_BLANK);
    }

    @Test
    @DisplayName("20세 미만일 경우 회원가입에 실패한다.")
    void failCreateMemberUnder20() {
        assertThatThrownBy(() -> Member.create("급식이", "test@test.com", "20150101", Gender.FEMALE))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("errorCode", GlobalErrorCode.UNDER_20_CANNOT_JOIN);
    }

    @Test
    @DisplayName("생년월일 형식이 올바르지 않을 경우 회원가입에 실패한다.")
    void failCreateMemberInvalidBrith() {
        assertThatThrownBy(() -> Member.create("복복복이", "test@test.com", "2015-01-01", Gender.FEMALE))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("errorCode", GlobalErrorCode.INVALID_BIRTH_FORMAT);
    }

    @Test
    @DisplayName("회원 삭제 시 isDeleted 상태가 true가 된다")
    void deleteMemberSuccess() {
        // given
        String name = "복복복";
        String email = "test@test.com";
        String birth = "20000101";

        Member member = Member.create(name, email, birth, Gender.FEMALE);

        // when
        member.delete();

        // then
        assertThat(member.isDeleted()).isTrue();
    }
}