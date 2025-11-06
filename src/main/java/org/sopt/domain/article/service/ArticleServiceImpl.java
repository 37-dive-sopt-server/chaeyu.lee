package org.sopt.domain.article.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.article.domain.Article;
import org.sopt.domain.article.dto.request.ArticleCreateRequestDto;
import org.sopt.domain.article.dto.response.ArticleResponseDto;
import org.sopt.domain.article.repository.ArticleRepository;
import org.sopt.domain.member.domain.Member;
import org.sopt.domain.member.repository.MemberRepository;
import org.sopt.global.exception.CustomException;
import org.sopt.global.exception.constant.GlobalErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long createArticle(ArticleCreateRequestDto request) {

        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new CustomException(GlobalErrorCode.MEMBER_NOT_FOUND));

        Article article = Article.create(
                member,
                request.tag(),
                request.title(),
                request.content()
        );

        articleRepository.save(article);

        return article.getId();
    }


}
