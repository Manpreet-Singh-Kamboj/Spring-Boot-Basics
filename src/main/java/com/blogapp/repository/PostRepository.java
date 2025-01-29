package com.blogapp.repository;

import com.blogapp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,String> {
    @Query(value = "SELECT p from Post AS p INNER JOIN categories AS c WHERE c.id = :categoryId")
    List<Post> findAllByCategoryId(@Param("categoryId") String categoryId);

}
