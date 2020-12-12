package com.cloudapps.springbooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cloudapps.springbooks.model.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	@Query(
			value = "SELECT * "
					+ "FROM comment c "
					+ "WHERE c.user_id = ("
						+ "SELECT DISTINCT u.id "
						+ "FROM user u "
						+ "WHERE u.nick = ?1)"
			, nativeQuery = true)
	List<Comment> findByUserNick(String nick);
}
