package com.hoangtuyen04work.socialnetwork.controller;


import com.hoangtuyen04work.socialnetwork.dto.request.NewPostRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.ApiResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.AuthenticationResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.PostResponse;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.service.impl.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController("/post")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {
    PostService postService;

    @GetMapping("/mypost")
    public ApiResponse<Set<PostResponse>> getAllPosts() throws AppException {
        return ApiResponse.<Set<PostResponse>>builder()
                .message("Success")
                .data(postService.getAll())
                .build();
    }

    @GetMapping("/post")
    public ApiResponse<Set<PostResponse>> getAllPost(){
        return ApiResponse.<Set<PostResponse>>builder()
                .message("Success")
                .data(postService.getAll())
                .build();
    }

    @PostMapping("/new")
    public ApiResponse<PostResponse> newPost(@RequestBody NewPostRequest newPostRequest) throws AppException {
        return ApiResponse.<PostResponse>builder()
                .message("created new post successfully")
                .data(postService.create(newPostRequest))
                .build();
    }

    @GetMapping("/{postId}")
    public ApiResponse<PostResponse> getPost(@PathVariable String postId) throws AppException {
        return ApiResponse.<PostResponse>builder()
                .message("get post successfully")
                .data(postService.getById(postId))
                .build();
    }

    @PutMapping("/edit/{postId}")
    public ApiResponse<AuthenticationResponse> editPost(@PathVariable String postId, @RequestBody NewPostRequest newPostRequest) throws AppException {
        postService.update(postId, newPostRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .message("edited post successfully")
                .data(null)
                .build();
    }

    @DeleteMapping("/delete/{postId}")
    public ApiResponse<AuthenticationResponse> deletePost(@PathVariable String postId) throws AppException {
        postService.delete(postId);
        return ApiResponse.<AuthenticationResponse>builder()
                .message("deleted post successfully")
                .data(null)
                .build();
    }
}
