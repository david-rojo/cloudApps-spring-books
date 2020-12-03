package com.cloudapps.springbooks.model;

public class Book {
	
	private Long id;
	
	private String title;
	
	private String summary;
	
	private String author;
	
	private String publisher;
	
	private int publicationYear;
	
	public Book() {
		
	}

	public Book(String title, String summary, String author, String publisher, int publicationYear) {
		super();
		this.title = title;
		this.summary = summary;
		this.author = author;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}	

}
