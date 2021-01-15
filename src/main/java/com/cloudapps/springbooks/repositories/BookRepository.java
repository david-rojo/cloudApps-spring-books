package com.cloudapps.springbooks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudapps.springbooks.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
