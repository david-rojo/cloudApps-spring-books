package com.cloudapps.springbooks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudapps.springbooks.model.entity.Comment;
import com.cloudapps.springbooks.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository comments;
	
	@Autowired
	public CommentService() {
		
	}
	
	public List<Comment> findAll() {
		return this.comments.findAll();
	}
	
	public Optional<Comment> findById(Long id) {
		
		return this.comments.findById(id);
	}
	
	public void save(Comment comment) {
		
		this.comments.save(comment);
	}
	
	public void delete(Comment comment) {
		this.comments.delete(comment);
	}
		
	public boolean existsComment(Long id) {
		return this.comments.findById(id).isPresent() ? true : false; 
	}
	
	public boolean isScoreValid(int score) {
		return score >=0 && score <=5 ? true : false;
	}
	
	public List<Comment> findByNick(String nick) {
		return this.comments.findByUserNick(nick);
	}
	
}
