package com.hoangtuyen04work.socialnetwork.service.interfaces;

import com.hoangtuyen04work.socialnetwork.dto.request.CommentRequest;
import com.hoangtuyen04work.socialnetwork.dto.request.PostIdRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.CommentResponse;
import com.hoangtuyen04work.socialnetwork.exception.AppException;

import java.util.Set;

public interface CommentServiceInterface {
    CommentResponse add(CommentRequest commentRequest) throws AppException;
    Set<CommentResponse> getAll(PostIdRequest postIdRequest) throws AppException;
    CommentResponse get(String id) throws AppException;
    CommentResponse update(CommentRequest commentRequest) throws AppException;
    void delete(String id) throws AppException;
}
