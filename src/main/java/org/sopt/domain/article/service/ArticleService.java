package org.sopt.domain.article.service;

import org.sopt.domain.article.dto.request.ArticleCreateRequestDto;
import org.sopt.domain.article.dto.response.ArticleDetailResponseDto;
import org.sopt.domain.article.dto.response.ArticleListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {
    Long createArticle(ArticleCreateRequestDto request);
    ArticleDetailResponseDto findOne(Long articleId);
    Page<ArticleListResponseDto> findAllArticles(String keyword, Pageable pageable);
}
