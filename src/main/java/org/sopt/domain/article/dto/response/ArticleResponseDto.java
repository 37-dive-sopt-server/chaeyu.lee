package org.sopt.domain.article.dto.response;

import lombok.Builder;
import org.sopt.domain.article.domain.Article;
import org.sopt.domain.article.domain.enums.Tag;

import java.time.LocalDateTime;

@Builder
public record ArticleResponseDto(
        Long id,
        Long memberId,
        String memberName,
        Tag tag,
        String title,
        String content,
        LocalDateTime publishedAt) {

    public static ArticleResponseDto fromEntity(Article article) {
        return ArticleResponseDto.builder()
                .id(article.getId())
                .memberId(article.getMember().getId())
                .memberName(article.getMember().getName())
                .tag(article.getTag())
                .title(article.getTitle())
                .content(article.getContent())
                .build();
    }
}
