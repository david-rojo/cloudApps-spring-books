package com.cloudapps.springbooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudapps.springbooks.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByNick(String nick);
}
