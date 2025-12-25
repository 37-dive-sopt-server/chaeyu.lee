package org.sopt.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentUpdateRequestDto(@NotBlank @Size(max = 300) String content) {
}

