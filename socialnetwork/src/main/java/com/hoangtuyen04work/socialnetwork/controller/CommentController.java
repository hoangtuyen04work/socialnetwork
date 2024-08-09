package com.hoangtuyen04work.socialnetwork.controller;


import com.hoangtuyen04work.socialnetwork.dto.request.CommentRequest;
import com.hoangtuyen04work.socialnetwork.dto.request.PostIdRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.ApiResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.CommentResponse;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.service.impl.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController("/comment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentController {
    CommentService commentService;

    @PostMapping("/new")
    public ApiResponse<CommentResponse> newComment(@RequestBody CommentRequest commentRequest) throws AppException {
        return ApiResponse.<CommentResponse>builder()
                .message("add comment successfully")
                .data(commentService.add(commentRequest))
                .build();
    }

    @GetMapping("/all")
    public ApiResponse<Set<CommentResponse>> getAllComments(PostIdRequest postIdRequest) throws AppException {
        return ApiResponse.<Set<CommentResponse>>builder()
                .data(commentService.getAll(postIdRequest))
                .message("get all comment successfully")
                .build();
    }

    @PutMapping("/{commentId}")
    public ApiResponse<CommentResponse> edit(@RequestBody CommentRequest commentRequest) throws AppException {
        return ApiResponse.<CommentResponse>builder()
                .message("edit comment successfully")
                .data(commentService.update(commentRequest))
                .build();
    }

    @DeleteMapping("/{commentId}")
    public ApiResponse<String> deleteComment(@PathVariable("commentId") String commentId) throws AppException {
        commentService.delete(commentId);
        return ApiResponse.<String>builder()
                .message("delete comment successfully")
                .data(null)
                .build();
    }

}
