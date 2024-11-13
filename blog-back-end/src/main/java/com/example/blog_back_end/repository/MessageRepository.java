package com.example.blog_back_end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog_back_end.model.Message;
import com.example.blog_back_end.model.User;

@Repository
public interface MessageRepository extends JpaRepository<Message,String> {
    List<Message> findByReceiver(User receiver);
}
