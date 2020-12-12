package com.cloudapps.springbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudapps.springbooks.model.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
