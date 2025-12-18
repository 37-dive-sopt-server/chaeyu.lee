package org.sopt.domain.article.service;

import org.sopt.domain.article.dto.request.ArticleCreateRequestDto;
import org.sopt.domain.article.dto.response.ArticleDetailResponseDto;
import org.sopt.domain.article.dto.response.ArticleListResponseDto;

import java.util.List;

public interface ArticleService {
    Long createArticle(ArticleCreateRequestDto request);
    ArticleDetailResponseDto findOne(Long articleId);
    List<ArticleListResponseDto> findAllArticles();
}
