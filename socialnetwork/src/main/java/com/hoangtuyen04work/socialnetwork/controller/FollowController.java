package com.hoangtuyen04work.socialnetwork.controller;


import com.hoangtuyen04work.socialnetwork.dto.request.FollowRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.ApiResponse;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.service.impl.FollowService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FollowController {
    FollowService followService;

    @GetMapping("/user/follow")
    public ApiResponse<String> follow(@RequestBody FollowRequest followRequest) throws AppException {
        followService.addAndRemove(followRequest.getFollowingid());
        return ApiResponse.<String>builder()
                .message("Successfully followed user")
                .data(null)
                .build();
    }

    @GetMapping("/user/count/follower/{userId}")
    public ApiResponse<Long> countFollower(@PathVariable String userId) throws AppException {
        return ApiResponse.<Long>builder()
                .message("Successfully counted followers")
                .data(followService.countFollowers(userId))
                .build();
    }

    @GetMapping("/user/count/following/{userId}")
    public ApiResponse<Long> countFollowing(@PathVariable String userId) throws AppException {
        return ApiResponse.<Long>builder()
                .message("Successfully counted following")
                .data( followService.countFollowings(userId))
                .build();
    }
}