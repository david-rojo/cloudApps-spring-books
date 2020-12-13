package com.cloudapps.springbooks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudapps.springbooks.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByNick(String nick);
}
