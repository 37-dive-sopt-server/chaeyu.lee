package org.sopt.domain.comment.service;

import org.sopt.domain.comment.dto.request.CommentCreateRequestDto;
import org.sopt.domain.comment.dto.response.CommentResponseDto;

public interface CommentService {
    CommentResponseDto createComment(Long articleId, CommentCreateRequestDto commentCreateRequestDto);
}
