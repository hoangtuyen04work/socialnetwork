package com.hoangtuyen04work.socialnetwork.repository;

import com.hoangtuyen04work.socialnetwork.entity.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FollowRepository extends JpaRepository<FollowEntity, String> {
    void deleteAllByFollowingId(String followingId);
    void deleteAllByFollowerId(String followerId);
    void deleteByFollowerIdAndFollowingId(String followerId, String followingId);
    boolean existsByFollowerIdAndFollowingId(String followerId, String followingId);
    long countByFollowingId(String followingId);
    long countByFollowerId(String followerId);
}
