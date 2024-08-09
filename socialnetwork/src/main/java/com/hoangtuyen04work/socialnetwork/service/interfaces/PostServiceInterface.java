package com.hoangtuyen04work.socialnetwork.service.interfaces;

import com.hoangtuyen04work.socialnetwork.dto.request.NewPostRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.PostResponse;
import com.hoangtuyen04work.socialnetwork.entity.PostEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;

import java.util.Set;

public interface PostServiceInterface {
    Set<PostResponse> getAll() throws AppException;
    PostEntity findById(String postId) throws AppException;
    PostResponse getById(String postId) throws AppException;
    PostResponse create(NewPostRequest newPostRequest) throws AppException;
    PostResponse update(String postId, NewPostRequest newPostRequest) throws AppException;
    void delete(String postId) throws AppException;
    boolean exists(String postId) throws AppException;
}


