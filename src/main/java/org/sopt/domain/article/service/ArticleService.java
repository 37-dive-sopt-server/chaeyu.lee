package org.sopt.domain.article.service;

import org.sopt.domain.article.dto.request.ArticleCreateRequestDto;

public interface ArticleService {
    Long createArticle(ArticleCreateRequestDto request);
}
