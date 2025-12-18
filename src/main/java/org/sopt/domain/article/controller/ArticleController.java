package org.sopt.domain.article.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.article.dto.request.ArticleCreateRequestDto;
import org.sopt.domain.article.dto.response.ArticleDetailResponseDto;
import org.sopt.domain.article.dto.response.ArticleListResponseDto;
import org.sopt.domain.article.service.ArticleService;
import org.sopt.domain.comment.domain.Comment;
import org.sopt.domain.comment.dto.request.CommentCreateRequestDto;
import org.sopt.domain.comment.dto.request.CommentUpdateRequestDto;
import org.sopt.domain.comment.dto.response.CommentResponseDto;
import org.sopt.domain.comment.service.CommentService;
import org.sopt.global.exception.SuccessCode.ArticleSuccessCode;
import org.sopt.global.exception.SuccessCode.CommentSuccessCode;
import org.sopt.global.response.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;

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
        ArticleDetailResponseDto response = articleService.findOne(articleId);

        return BaseResponse.ok(ArticleSuccessCode.GET_ARTICLE_SUCCESS.getMsg(), response);
    }

    @GetMapping
    public BaseResponse<List<ArticleListResponseDto>> getAllArticles() {
        List<ArticleListResponseDto> response = articleService.findAllArticles();
        return BaseResponse.ok(ArticleSuccessCode.GET_ALL_ARTICLES_SUCCESS.getMsg(), response);
    }

    @PostMapping("/{articleId}/comments")
    public BaseResponse<CommentResponseDto> createComment(@PathVariable Long articleId,
                                                          @Valid @RequestBody CommentCreateRequestDto commentCreateRequestDto) {
        CommentResponseDto response = commentService.createComment(articleId, commentCreateRequestDto);
        return BaseResponse.ok(CommentSuccessCode.CREATE_COMMENT_SUCCESS.getMsg(), response);
    }

    @PutMapping("/comments/{commentId}")
    public BaseResponse<CommentResponseDto> updateComment(@PathVariable Long commentId,
                                                          @Valid @RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {
        CommentResponseDto response = commentService.updateComment(commentId, commentUpdateRequestDto);
        return BaseResponse.ok(CommentSuccessCode.UPDATE_COMMENT_SUCCESS.getMsg(), response);
    }

    @DeleteMapping("/comments/{commentId}")
    public BaseResponse<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return BaseResponse.ok(CommentSuccessCode.DELETE_COMMENT_SUCCESS.getMsg(), null);
    }

    @GetMapping("/comments/{commentId}")
    public BaseResponse<CommentResponseDto> getComment(@PathVariable Long commentId){
        CommentResponseDto response = commentService.getComment(commentId);
        return BaseResponse.ok(CommentSuccessCode.GET_COMMENT_SUCCESS.getMsg(), response);
    }
}


