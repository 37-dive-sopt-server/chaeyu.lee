package org.sopt.domain.article.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.article.dto.request.ArticleCreateRequestDto;
import org.sopt.domain.article.dto.response.ArticleResponseDto;
import org.sopt.domain.article.service.ArticleService;
import org.sopt.global.exception.constant.ArticleSuccessCode;
import org.sopt.global.response.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{articleId}")
    public BaseResponse getArticle(
            @PathVariable Long articleId
    ) {
        ArticleResponseDto response = articleService.findOne(articleId);

        return BaseResponse.ok(ArticleSuccessCode.GET_ARTICLE_SUCCESS.getMsg(), response);
    }

    @GetMapping
    public BaseResponse<List<ArticleResponseDto>> getAllArticles() {
        List<ArticleResponseDto> response = articleService.findAllArticles();
        return BaseResponse.ok(ArticleSuccessCode.GET_ALL_ARTICLES_SUCCESS.getMsg(), response);
    }
}


