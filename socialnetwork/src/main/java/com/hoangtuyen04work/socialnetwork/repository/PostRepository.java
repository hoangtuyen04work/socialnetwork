package com.hoangtuyen04work.socialnetwork.repository;

import com.hoangtuyen04work.socialnetwork.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, String> {
    void deleteAllByUserId(String userId);
    Set<PostEntity> findAllByUserId(String userId);
    Set<PostEntity> findAllById(String id);
}
