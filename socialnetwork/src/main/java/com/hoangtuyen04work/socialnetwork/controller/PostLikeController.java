package com.hoangtuyen04work.socialnetwork.controller;


import com.hoangtuyen04work.socialnetwork.dto.request.PostLikeRequest;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.service.impl.PostLikeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostLikeController {
    PostLikeService postLikeService;

    @GetMapping("/count/{postId}")
    public Long countLikes(@PathVariable String postId) {
        return postLikeService.countLikes(postId);
    }

    @PutMapping
    public String likePost(@RequestBody PostLikeRequest postLikeRequest) throws AppException {
        postLikeService.addAndRemove(postLikeRequest);
        return "Sucessfully like post";
    }
}
