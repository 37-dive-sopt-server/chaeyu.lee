package org.sopt.domain.article.dto.response;

import lombok.Builder;
import org.sopt.domain.article.domain.Article;
import org.sopt.domain.article.domain.enums.Tag;

import java.time.LocalDateTime;

@Builder
public record ArticleListResponseDto(
        Long id,
        String memberName,
        Tag tag,
        String title,
        LocalDateTime createdAt) {
    public static ArticleListResponseDto fromEntity(Article article) {
        return ArticleListResponseDto.builder()
                .id(article.getId())
                .memberName(article.getMember().getName())
                .tag(article.getTag())
                .title(article.getTitle())
                .createdAt(article.getCreatedAt())
                .build();
    }
}
