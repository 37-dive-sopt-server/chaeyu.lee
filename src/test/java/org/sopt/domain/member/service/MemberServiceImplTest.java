package org.sopt.domain.member.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sopt.domain.member.domain.Member;
import org.sopt.domain.member.domain.enums.Gender;
import org.sopt.domain.member.dto.request.MemberCreateRequestDto;
import org.sopt.domain.member.repository.MemberRepository;
import org.sopt.global.exception.CustomException;
import org.sopt.global.exception.ErrorCode.GlobalErrorCode;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Test
    @DisplayName("회원가입 성공 - 중복 이메일이 없으면 저장된다")
    void joinSuccess() {
        // given
        MemberCreateRequestDto request = new MemberCreateRequestDto("복복이", "19950101", "test@sopt.org", "여성");
        given(memberRepository.findByEmail(anyString())).willReturn(Optional.empty());

        // when
        memberService.join(request);

        // then
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    @DisplayName("회원가입 실패 - 이메일 중복 시 예외가 발생한다")
    void joinFailDuplicateEmail() {
        // given
        MemberCreateRequestDto request = new MemberCreateRequestDto("복복이", "test@sopt.org", "19950101", "여성");
        given(memberRepository.findByEmail(request.getEmail())).willReturn(Optional.of(mock(Member.class)));

        // when & then
        assertThatThrownBy(() -> memberService.join(request))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("errorCode", GlobalErrorCode.DUPLICATE_EMAIL);
    }

    @Test
    @DisplayName("회원 삭제 실패 - 이미 삭제된 회원은 예외가 발생한다")
    void deleteMemberFailAlreadyDeleted() {
        // given
        Long memberId = 1L;
        Member member = Member.create("복복이", "test@sopt.org", "19950101", Gender.FEMALE);
        member.delete();

        given(memberRepository.findByIncludedDeleted(memberId)).willReturn(Optional.of(member));

        // when & then
        assertThatThrownBy(() -> memberService.deleteMember(memberId))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("errorCode", GlobalErrorCode.ALREADY_DELETED_MEMBER);
    }

    @Test
    @DisplayName("조회 실패 - 존재하지 않는 회원 ID면 예외가 발생한다")
    void findOneFailNotFound() {
        // given
        Long memberId = 999L;
        given(memberRepository.findById(memberId)).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> memberService.findOne(memberId))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("errorCode", GlobalErrorCode.MEMBER_NOT_FOUND);
    }


}