package org.sopt.domain.comment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.sopt.domain.comment.domain.Comment;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
public class CommentResponseDto {
    private final Long id;
    private final String memberName;
    private final String content;
    private final LocalDateTime createdAt;

    public static CommentResponseDto fromEntity(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .memberName(comment.getMember().getName())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
