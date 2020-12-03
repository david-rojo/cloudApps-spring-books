package com.cloudapps.springbooks.service;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.cloudapps.springbooks.model.entity.Comment;

@Service
public class CommentService {

	private ConcurrentMap<Long, ConcurrentMap<Long, Comment>> comments = new ConcurrentHashMap<>();
	private AtomicLong nextId = new AtomicLong();
	
	public CommentService() {
		this.save(new Comment("Very interesting", "Paul", 4), 0L);
		this.save(new Comment("I love it", "Sue", 5), 1L);
	}
	
	public Collection<ConcurrentMap<Long, Comment>> findAll() {
		return comments.values();
	}
	
	public Collection<Comment> findAllCommentsByBook(Long bookId){
		if (!this.isBookPresent(bookId)) {
			return Collections.emptyList();
		}
		return comments.get(bookId).values();
	}
	
	public Comment findById(Long bookId, Long commentId) {
		Comment comment = null;
		if (this.isBookPresent(bookId)) {
			comment = comments.get(bookId).get(commentId);
		}
		return comment;
	}
	
	public void save(Comment comment, Long bookId) {
		long id = nextId.getAndIncrement();
		comment.setId(id);
		if (this.comments.get(bookId) == null) {
			this.saveBook(bookId);
		}
		this.comments.get(bookId).put(id, comment);
	}
	
	public boolean deleteById(Long bookId, Long commentId) {
		if (this.isBookPresent(bookId)) {
			this.comments.get(bookId).remove(commentId);
			if (this.comments.get(bookId).size() == 0) {
				this.deleteBook(bookId);
			}
			return true;			
		}
		return false;
	}
	
	private void saveBook(Long bookId) {
		this.comments.put(bookId, new ConcurrentHashMap<>());
	}
	
	private void deleteBook(Long bookId) {
		this.comments.remove(bookId);
	}
	
	private boolean isBookPresent(Long bookId) {
		return this.comments.get(bookId) == null ? false : true; 
	}
	
}
