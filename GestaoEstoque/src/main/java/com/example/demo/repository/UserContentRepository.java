package com.example.demo.repository;

import com.example.demo.model.UserContent;
import com.example.demo.model.UserContentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContentRepository extends JpaRepository<UserContent, UserContentId> {
}