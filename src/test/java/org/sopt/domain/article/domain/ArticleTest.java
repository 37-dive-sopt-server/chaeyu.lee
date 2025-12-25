package org.sopt.domain.article.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sopt.domain.article.domain.enums.Tag;
import org.sopt.domain.member.domain.Member;
import org.sopt.domain.member.domain.enums.Gender;

import static org.assertj.core.api.Assertions.assertThat;

class ArticleTest {

    @Test
    @DisplayName("아티클 생성 정적 팩토리 메서드가 정상 작동한다.")
    void articleCreateSuccess() {
        // given
        Member member = Member.create("작성자", "test@sopt.org", "20000101", Gender.MALE);

        // when
        Article article = Article.create(member, Tag.ETC, "제목", "내용");

        // then
        assertThat(article.getMember()).isEqualTo(member);
        assertThat(article.getTitle()).isEqualTo("제목");
        assertThat(article.getTag()).isEqualTo(Tag.ETC);
    }

}