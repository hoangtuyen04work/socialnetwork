package com.hoangtuyen04work.socialnetwork.service.interfaces;

import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;

public interface UserServiceInterface {
    UserEntity create(UserEntity userEntity);
    void delete() throws AppException;
    boolean existed(String id);
}
