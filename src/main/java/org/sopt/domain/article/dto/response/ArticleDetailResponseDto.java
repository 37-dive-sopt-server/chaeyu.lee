package org.sopt.domain.article.dto.response;

import lombok.Builder;
import org.sopt.domain.article.domain.Article;
import org.sopt.domain.article.domain.enums.Tag;
import org.sopt.domain.comment.domain.Comment;
import org.sopt.domain.comment.dto.response.CommentResponseDto;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ArticleDetailResponseDto(
        Long id,
        Long memberId,
        String memberName,
        Tag tag,
        String title,
        String content,
        List<CommentResponseDto> comments,
        LocalDateTime publishedAt) {

    public static ArticleDetailResponseDto fromEntity(Article article, List<Comment> comments) {
        return ArticleDetailResponseDto.builder()
                .id(article.getId())
                .memberId(article.getMember().getId())
                .memberName(article.getMember().getName())
                .tag(article.getTag())
                .title(article.getTitle())
                .content(article.getContent())
                .comments(comments.stream()
                        .map(CommentResponseDto::fromEntity)
                        .toList())
                .publishedAt(article.getCreatedAt())
                .build();
    }
}
