package com.hoangtuyen04work.socialnetwork.service.interfaces;

import com.hoangtuyen04work.socialnetwork.exception.AppException;

public interface FollowServiceInterface {
    boolean addAndRemove(String followerId) throws AppException;
    boolean isFollowing(String followerId, String followingId);
    long countFollowers(String followerId) throws AppException;
    long countFollowings(String followingId) throws AppException;
}
