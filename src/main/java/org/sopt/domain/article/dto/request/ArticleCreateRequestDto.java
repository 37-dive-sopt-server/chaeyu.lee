package org.sopt.domain.article.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.sopt.domain.article.domain.enums.Tag;

@Builder
public record ArticleCreateRequestDto(
        @NotNull(message = "작성자 ID는 필수입니다.")
        Long memberId,

        @NotNull(message = "태그는 필수입니다.")
        Tag tag,

        @NotBlank(message = "제목은 공백일 수 없습니다.")
        String title,

        String content
) {
}
