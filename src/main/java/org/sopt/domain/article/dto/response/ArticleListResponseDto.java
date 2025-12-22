package org.sopt.domain.article.dto.response;

import lombok.*;
import org.sopt.domain.article.domain.Article;
import org.sopt.domain.article.domain.enums.Tag;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class ArticleListResponseDto {
    private Long id;
    private String memberName;
    private Tag tag;
    private String title;
    private LocalDateTime createdAt;

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