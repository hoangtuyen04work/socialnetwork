package com.hoangtuyen04work.socialnetwork.repository;

import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    boolean existsByUserId(String userId);
    
    boolean existsById(String Id);

    Optional<UserEntity> findByUserId(String userId);

    Optional<UserEntity> findById(String id);

    boolean existsByEmail(String email);

    UserEntity findByEmail(String email);
}