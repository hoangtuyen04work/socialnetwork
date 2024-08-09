package com.hoangtuyen04work.socialnetwork.service.impl;

import com.hoangtuyen04work.socialnetwork.dto.request.UserRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.UserResponse;
import com.hoangtuyen04work.socialnetwork.entity.RoleEntity;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.exception.ErrorCode;
import com.hoangtuyen04work.socialnetwork.mapper.UserMapper;
import com.hoangtuyen04work.socialnetwork.repository.*;
import com.hoangtuyen04work.socialnetwork.service.interfaces.UserServiceInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class UserService implements UserServiceInterface {
    UserRepository userRepository;
    UserMapper userMapper;
    RoleService roleService;


    public UserEntity save(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    public boolean isYourSelf(String id){
        return Objects.equals(id, getIdInHolder());
    }


    public UserResponse editName(String id, String name) throws AppException {
        if( ! isYourSelf(id)) throw new AppException(ErrorCode.NOT_AUTHENTICATED);
        UserEntity userEntity = findById(getIdInHolder());
        userEntity.setUserName(name);
        return userMapper.toUserResponse(save(userEntity));
    }

    public UserEntity findById(String id) throws AppException {
        return userRepository.findById(id).orElseThrow(()-> new AppException((ErrorCode.USER_EXISTED)));
    }

    public void setRoleUser(UserEntity userEntity){
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleService.findRole("USER"));
        userEntity.setRoles(roles);
    }
    public UserResponse updateName(String name){
        UserEntity userEntity = getUserInHolder();
        userEntity.setUserName(name);
        return userMapper.toUserResponse(userEntity);
    }


    public UserEntity findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public UserResponse getByUserId(String userId) throws AppException {
        UserEntity userEntity = userRepository.findByUserId(userId).orElseThrow(
                ()->new AppException(ErrorCode.USER_NOT_EXISTED));
        return getInfo(userEntity.getId());
    }

    public UserResponse getInfo(String id){
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(Objects.equals(getIdInHolder(), id)) {
            return userMapper.toUserResponse(userEntity.get()).hideSensitiveInfo();
        }
        else{
            return userMapper.toUserResponse(userEntity.get());
        }
    }

    @Override
    public UserEntity create(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    //chua lam
    @PreAuthorize("authentication.name == userRequest.id")
    public UserEntity updatePassword(UserRequest userRequest) throws AppException {
        String id = getIdInHolder();
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userRepository.save(userEntity);
    }

    @Override
    public void delete() throws AppException {
        UserEntity userEntity = userRepository.findById(getIdInHolder())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        userRepository.delete(userEntity);
    }

    @Override
    public boolean existed(String id) {
        return userRepository.existsById(id);
    }

    private String getIdInHolder(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    private UserEntity getUserInHolder(){
        return userRepository.findById(getIdInHolder()).get();
    }
}
