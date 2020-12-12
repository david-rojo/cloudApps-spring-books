package com.cloudapps.springbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudapps.springbooks.model.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
