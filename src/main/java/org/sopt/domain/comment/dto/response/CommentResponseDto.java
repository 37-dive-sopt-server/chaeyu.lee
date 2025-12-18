package org.sopt.domain.comment.dto.response;

import lombok.Builder;
import org.sopt.domain.comment.domain.Comment;

import java.time.LocalDateTime;

@Builder
public record CommentResponseDto(Long id, String memberName, String content, LocalDateTime createdAt) {
    public static CommentResponseDto fromEntity(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .memberName(comment.getMember().getName())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
