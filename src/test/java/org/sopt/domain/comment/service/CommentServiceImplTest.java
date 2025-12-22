package org.sopt.domain.comment.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sopt.domain.article.domain.enums.Tag;
import org.sopt.domain.article.dto.request.ArticleCreateRequestDto;
import org.sopt.domain.article.repository.ArticleRepository;
import org.sopt.domain.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cache.CacheManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Transactional
class CommentServiceImplTest {

    @Autowired
    private ArticleService articleService;

    @SpyBean
    private ArticleRepository articleRepository;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    void clearCache() {
        cacheManager.getCache("articleList").clear();
    }

    @Test
    @DisplayName("목록 조회 시 최초 1회만 DB에 접근하고 이후에는 캐시를 사용한다")
    void getArticleListWithCaching() {
        // when
        IntStream.range(0, 10).forEach(i -> articleService.findAllArticles());

        // then
        verify(articleRepository, times(1)).findAllWithMember();
    }

    @Test
    @DisplayName("새 글 작성 시 기존 목록 캐시가 삭제되어 DB를 다시 조회한다")
    void getArticleListWithCacheEvict() {
        IntStream.range(0, 10).forEach(i -> articleService.findAllArticles());

        ArticleCreateRequestDto request = new ArticleCreateRequestDto(1L, Tag.ETC, "New Title", "Content");
        articleService.createArticle(request);

        IntStream.range(0, 10).forEach(i -> articleService.findAllArticles());

        verify(articleRepository, times(2)).findAllWithMember();
    }

}