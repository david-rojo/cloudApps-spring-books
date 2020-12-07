package com.cloudapps.springbooks.service;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cloudapps.springbooks.model.entity.Comment;

@Service
public class CommentService {

	private Logger log = LoggerFactory.getLogger(CommentService.class);
	
	private ConcurrentMap<Long, ConcurrentMap<Long, Comment>> comments = new ConcurrentHashMap<>();
	private AtomicLong nextId = new AtomicLong();
	
	@Autowired
	public CommentService(@Value("${springbooks.config.startup.dataload:false}") boolean startupDataLoad) {
		if (startupDataLoad) {
			this.save(new Comment("Very interesting", "enric", 4), 0L);
			this.save(new Comment("I love it", "malena", 5), 1L);
			log.info("Startup comment load completed");
		}
		else {
			log.info("Startup comment load disabled");
		}
		
	}
	
	public Collection<ConcurrentMap<Long, Comment>> findAll() {
		return comments.values();
	}
	
	public Collection<Comment> findAllCommentsByBook(Long bookId){
		if (!this.existsBook(bookId)) {
			return Collections.emptyList();
		}
		return comments.get(bookId).values();
	}
	
	public Comment findById(Long bookId, Long commentId) {
		Comment comment = null;
		if (this.existsBook(bookId)) {
			comment = comments.get(bookId).get(commentId);
		}
		return comment;
	}
	
	public Comment save(Comment comment, Long bookId) {
		long id = nextId.getAndIncrement();
		comment.setId(id);
		if (!this.existsBook(bookId)) {
			this.saveBook(bookId);
		}
		this.comments.get(bookId).put(id, comment);
		return comment;
	}
	
	public void deleteById(Long bookId, Long commentId) {
		if (this.existsBook(bookId) && this.existsComment(bookId, commentId)) {
			this.comments.get(bookId).remove(commentId);
			if (this.comments.get(bookId).size() == 0) {
				this.deleteBook(bookId);
			}		
		}
	}
	
	private void saveBook(Long bookId) {
		this.comments.put(bookId, new ConcurrentHashMap<>());
	}
	
	private void deleteBook(Long bookId) {
		this.comments.remove(bookId);
	}
	
	private boolean existsBook(Long bookId) {
		return this.comments.get(bookId) == null ? false : true; 
	}
	
	private boolean existsComment(Long bookId, Long commentId) {
		return this.comments.get(bookId).get(commentId) == null ? false : true; 
	}
	
	public boolean isScoreValid(int score) {
		return score >=0 && score <=5 ? true : false;
	}
	
}
