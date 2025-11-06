package org.sopt.domain.article.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.article.dto.request.ArticleCreateRequestDto;
import org.sopt.domain.article.dto.response.ArticleResponseDto;
import org.sopt.domain.article.service.ArticleService;
import org.sopt.global.exception.constant.ArticleSuccessCode;
import org.sopt.global.response.BaseResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    public BaseResponse<Long> createArticle(
            @Valid @RequestBody ArticleCreateRequestDto request
    ) {
        Long articleId = articleService.createArticle(request);
        return BaseResponse.create(ArticleSuccessCode.CREATE_ARTICLE_SUCCESS.getMsg(), articleId);
    }

}
