package org.sopt.domain.article.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.article.domain.Article;
import org.sopt.domain.article.dto.request.ArticleCreateRequestDto;
import org.sopt.domain.article.dto.response.ArticleDetailResponseDto;
import org.sopt.domain.article.dto.response.ArticleListResponseDto;
import org.sopt.domain.article.repository.ArticleRepository;
import org.sopt.domain.comment.domain.Comment;
import org.sopt.domain.comment.repository.CommentRepository;
import org.sopt.domain.member.domain.Member;
import org.sopt.domain.member.repository.MemberRepository;
import org.sopt.global.exception.CustomException;
import org.sopt.global.exception.ErrorCode.GlobalErrorCode;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    @CacheEvict(value = "articleList", allEntries = true, cacheManager = "cacheManager")
    public Long createArticle(ArticleCreateRequestDto request) {

        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new CustomException(GlobalErrorCode.MEMBER_NOT_FOUND));

        if (articleRepository.findByTitle(request.title()).isPresent()) {
            throw new CustomException(GlobalErrorCode.DUPLICATE_ARTICLE_TITLE);
        }

        Article article = Article.create(
                member,
                request.tag(),
                request.title(),
                request.content()
        );

        articleRepository.save(article);

        return article.getId();
    }

    @Override
    @Cacheable(value = "article", key = "#articleId", cacheManager = "cacheManager")
    public ArticleDetailResponseDto findOne(Long articleId) {
        Article article = articleRepository.findWithMemberById(articleId)
                .orElseThrow(() -> new CustomException(GlobalErrorCode.ARTICLE_NOT_FOUND));

        List<Comment> comments = commentRepository.findAllByArticleWithMember(article);
        return ArticleDetailResponseDto.fromEntity(article, comments);
    }

    @Override
    @Cacheable(value = "articleList", key = "'all'", cacheManager = "cacheManager")
    public List<ArticleListResponseDto> findAllArticles() {
        return articleRepository.findAllWithMember().stream()
                .map(ArticleListResponseDto::fromEntity)
                .toList();
    }
}
