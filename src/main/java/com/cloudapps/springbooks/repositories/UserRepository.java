package com.cloudapps.springbooks.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudapps.springbooks.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

}
