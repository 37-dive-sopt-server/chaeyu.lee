package org.sopt.domain.comment.service;

import org.sopt.domain.comment.dto.request.CommentCreateRequestDto;
import org.sopt.domain.comment.dto.request.CommentUpdateRequestDto;
import org.sopt.domain.comment.dto.response.CommentResponseDto;

public interface CommentService {
    CommentResponseDto createComment(Long articleId, CommentCreateRequestDto commentCreateRequestDto);
    CommentResponseDto updateComment(Long commentId, CommentUpdateRequestDto commentUpdateRequestDto);
    void deleteComment(Long commentId);
    CommentResponseDto getComment(Long commentId);
}
