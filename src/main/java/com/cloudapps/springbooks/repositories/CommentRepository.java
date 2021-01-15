package com.cloudapps.springbooks.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudapps.springbooks.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByBookIdAndId(Long bookId, Long commentId);

    Collection<Comment> findByUserId(long userId);
}
