package com.example.blog_back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog_back_end.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    boolean existsByUsername(String userName);
}
