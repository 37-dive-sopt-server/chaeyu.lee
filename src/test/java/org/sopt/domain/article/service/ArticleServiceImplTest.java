package org.sopt.domain.article.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sopt.domain.article.domain.Article;
import org.sopt.domain.article.domain.enums.Tag;
import org.sopt.domain.article.dto.request.ArticleCreateRequestDto;
import org.sopt.domain.article.repository.ArticleRepository;
import org.sopt.domain.member.domain.Member;
import org.sopt.domain.member.domain.enums.Gender;
import org.sopt.domain.member.repository.MemberRepository;
import org.sopt.global.exception.CustomException;
import org.sopt.global.exception.ErrorCode.GlobalErrorCode;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class ArticleServiceImplTest {
    @InjectMocks
    private ArticleServiceImpl articleService;

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private MemberRepository memberRepository;

    @Test
    @DisplayName("아티클 생성 시 중복된 제목이 있으면 예외가 발생한다.")
    void createArticleDuplicateTitleFail() {
        // given
        ArticleCreateRequestDto request = new ArticleCreateRequestDto(1L, Tag.ETC, "제목", "내용");
        Member member = Member.create("이름", "testest@sopt.org", "19900101", Gender.MALE);

        given(memberRepository.findById(anyLong())).willReturn(Optional.of(member));
        given(articleRepository.findByTitle(anyString())).willReturn(Optional.of(mock(Article.class)));

        // when & then
        CustomException exception = assertThrows(CustomException.class, () -> articleService.createArticle(request));
        assertThat(exception.getErrorCode()).isEqualTo(GlobalErrorCode.DUPLICATE_ARTICLE_TITLE);
    }
}