package com.hoangtuyen04work.socialnetwork.service.impl;

import com.hoangtuyen04work.socialnetwork.dto.request.PostLikeRequest;
import com.hoangtuyen04work.socialnetwork.entity.PostEntity;
import com.hoangtuyen04work.socialnetwork.entity.PostLikeEntity;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.repository.PostLikeRepository;
import com.hoangtuyen04work.socialnetwork.service.interfaces.PostLikeSeviceInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class PostLikeService implements PostLikeSeviceInterface {
    PostLikeRepository postLikeRepository;
    UserService userService;
    PostService postService;

    @Override
    public void addAndRemove(PostLikeRequest postLikeRequest) throws AppException{
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        PostLikeEntity postLikeEntity = setPostLikeAttribute(userId, postLikeRequest.getPostId());
        if(isLiked(postLikeEntity)){
            postLikeRepository.deleteByPostIdAndUserId(postLikeEntity.getUser().getId(), postLikeEntity.getPost().getId());
        }
        else{
            postLikeRepository.save(postLikeEntity);
        }
    }

    private PostLikeEntity setPostLikeAttribute(String userId, String postId) throws AppException {
        PostLikeEntity postLikeEntity = new PostLikeEntity();
        UserEntity userEntity = userService.findByUserId(userId);
        PostEntity postEntity = postService.findById(postId);
        postLikeEntity.setPost(postEntity);
        postLikeEntity.setUser(userEntity);
        return postLikeEntity;
    }


    @Override
    public boolean isLiked(PostLikeEntity postLikeEntity) throws AppException {
        return postLikeRepository.existsByPostIdAndUserId(postLikeEntity.getPost().getId()
                                                            , postLikeEntity.getUser().getId());
    }

    @Override
    public long countLikes(String postId) {
        return postLikeRepository.countLikedByPostId(postId);
    }
}
