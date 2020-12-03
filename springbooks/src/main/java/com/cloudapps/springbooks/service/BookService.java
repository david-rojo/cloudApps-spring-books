package com.cloudapps.springbooks.service;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.cloudapps.springbooks.model.entity.Book;

@Service
public class BookService {

	private ConcurrentMap<Long, Book> books = new ConcurrentHashMap<>();
	private AtomicLong nextId = new AtomicLong();
	
	public BookService() {
		this.save(new Book(
				"Don Quixote", 
				"Don Quixote, Spanish in full, Part 1 El ingenioso hidalgo don Quijote de la Mancha (“The Ingenious Hidalgo "
						+ "Don Quixote of La Mancha”) and Part 2 Segunda parte del ingenioso caballero don Quijote de la Mancha"
						+ " (“Second Part of the Ingenious Knight Don Quixote of La Mancha”), novel published in two parts "
						+ "(part 1, 1605, and part 2, 1615) by Spanish writer Miguel de Cervantes, one of the most widely read "
						+ "classics of Western literature. Originally conceived as a parody of the chivalric romances that had "
						+ "long been in literary vogue, it describes realistically what befalls an aging knight who, his head bemused "
						+ "by reading such romances, sets out on his old horse Rocinante, with his pragmatic squire, Sancho Panza, to "
						+ "seek adventure. Widely and immediately translated (first English translation 1612), the novel was a great "
						+ "and continuing success and is considered a prototype of the modern novel.",
				"Miguel de Cervantes",
				"CloudApps Classics",
				1512));
		this.save(new Book(
				"The Little Prince",
				"The Little Prince, French Le Petit Prince, fable and modern classic by French aviator and writer Antoine de "
						+ "Saint-Exupéry that was published with his own illustrations in French as Le Petit Prince in 1943. The simple tale "
						+ "tells the story of a child, the little prince, who travels the universe gaining wisdom. The novella has been "
						+ "translated into hundreds of languages and has sold some 200 million copies worldwide, making it one of the "
						+ "best-selling books in publishing history.",
				"Antoine de Saint-Exupéry",
				"CloudApps Classics",
				1943));
	}
	
	public Collection<Book> findAll() {
		return books.values();
	}
	
	public Optional<Book> findById(long id) {
		return Optional.ofNullable(books.get(id));
	}
	
	public void save(Book book) {
		long id = nextId.getAndIncrement();
		book.setId(id);
		this.books.put(id, book);
	}
	
	public boolean deleteById(long id) {
		if (this.isBookPresent(id)) {
			this.books.remove(id);
			return true;
		}
		return false;
	}	
	
	private boolean isBookPresent(Long id) {
		return this.books.get(id) == null ? false : true; 
	}
}
