package org.sopt.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.article.domain.Article;
import org.sopt.domain.article.repository.ArticleRepository;
import org.sopt.domain.comment.domain.Comment;
import org.sopt.domain.comment.dto.request.CommentCreateRequestDto;
import org.sopt.domain.comment.dto.request.CommentUpdateRequestDto;
import org.sopt.domain.comment.dto.response.CommentResponseDto;
import org.sopt.domain.comment.repository.CommentRepository;
import org.sopt.domain.member.domain.Member;
import org.sopt.domain.member.repository.MemberRepository;
import org.sopt.global.exception.CustomException;
import org.sopt.global.exception.ErrorCode.GlobalErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public CommentResponseDto createComment(Long articleId, CommentCreateRequestDto commentCreateRequestDto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new CustomException(GlobalErrorCode.ARTICLE_NOT_FOUND));
        Member member = memberRepository.findById(commentCreateRequestDto.memberId())
                .orElseThrow(() -> new CustomException(GlobalErrorCode.MEMBER_NOT_FOUND));

        Comment comment = Comment.create(commentCreateRequestDto.content(), article, member);
        commentRepository.save(comment);
        return CommentResponseDto.fromEntity(comment);
    }

    @Override
    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentUpdateRequestDto commentUpdateRequestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(GlobalErrorCode.COMMENT_NOT_FOUND));

        comment.updateComment(commentUpdateRequestDto.content());
        return CommentResponseDto.fromEntity(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(GlobalErrorCode.COMMENT_NOT_FOUND));

        commentRepository.delete(comment);
    }
}
