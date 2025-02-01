package com.blogapp.repository;

import com.blogapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,String> {
    Optional<Like> findByUserIdAndPostId(String userId,String postId);
    Optional<Like> findByUserIdAndCommentId(String userId, String commentId);
}
