package com.example.blog_back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog_back_end.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,String>{

}
