package com.kh.springdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springdb.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	
}
