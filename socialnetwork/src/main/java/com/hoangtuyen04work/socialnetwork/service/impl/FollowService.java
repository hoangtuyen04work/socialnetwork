package com.hoangtuyen04work.socialnetwork.service.impl;

import com.hoangtuyen04work.socialnetwork.entity.FollowEntity;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.repository.FollowRepository;
import com.hoangtuyen04work.socialnetwork.service.interfaces.FollowServiceInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class FollowService implements FollowServiceInterface {
    UserService userService;
    FollowRepository followRepository;

    @Override
    public boolean addAndRemove(String followingId) throws AppException {
        FollowEntity follow = setFollowAttribute(followingId);
        if(isFollowing(follow.getFollower().getId(), follow.getFollowing().getId())) {
            followRepository.deleteByFollowerIdAndFollowingId(follow.getFollower().getId(), follow.getFollowing().getId());
        }
        else{
            followRepository.save(follow);
        }
        return true;
    }

    private FollowEntity setFollowAttribute(String followingId) throws AppException {
        FollowEntity followEntity = new FollowEntity();
        UserEntity following = userService.findByUserId(followingId);
        UserEntity follower = userService.findByUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        followEntity.setFollower(follower);
        followEntity.setFollowing(following);
        return followEntity;
    }

    @Override
    public boolean isFollowing(String followerId, String followingId) {
        return followRepository.existsByFollowerIdAndFollowingId(followerId, followingId);
    }

    @Override
    public long countFollowers(String followerId) throws AppException {
        String id = userService.findByUserId(followerId).getId();
        return followRepository.countByFollowingId(id);
    }

    @Override
    public long countFollowings(String followingId) throws AppException {
        String id = userService.findByUserId(followingId).getId();
        return followRepository.countByFollowerId(id);
    }
}
