package com.example.blog_back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog_back_end.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    
}
