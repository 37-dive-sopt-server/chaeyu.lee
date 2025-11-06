package org.sopt.domain.article.service;

import org.sopt.domain.article.dto.request.ArticleCreateRequestDto;
import org.sopt.domain.article.dto.response.ArticleResponseDto;

public interface ArticleService {
    Long createArticle(ArticleCreateRequestDto request);
    ArticleResponseDto findOne(Long articleId);
}
