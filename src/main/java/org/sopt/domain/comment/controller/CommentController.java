package org.sopt.domain.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.comment.dto.request.CommentUpdateRequestDto;
import org.sopt.domain.comment.dto.response.CommentResponseDto;
import org.sopt.domain.comment.service.CommentService;
import org.sopt.global.exception.SuccessCode.CommentSuccessCode;
import org.sopt.global.response.BaseResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments/{commentId}")
public class CommentController {

    private final CommentService commentService;
    @PutMapping
    public BaseResponse<CommentResponseDto> updateComment(@PathVariable Long commentId,
                                                          @Valid @RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {
        CommentResponseDto response = commentService.updateComment(commentId, commentUpdateRequestDto);
        return BaseResponse.ok(CommentSuccessCode.UPDATE_COMMENT_SUCCESS.getMsg(), response);
    }

    @DeleteMapping
    public BaseResponse<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return BaseResponse.ok(CommentSuccessCode.DELETE_COMMENT_SUCCESS.getMsg(), null);
    }

    @GetMapping
    public BaseResponse<CommentResponseDto> getComment(@PathVariable Long commentId){
        CommentResponseDto response = commentService.getComment(commentId);
        return BaseResponse.ok(CommentSuccessCode.GET_COMMENT_SUCCESS.getMsg(), response);
    }
}
