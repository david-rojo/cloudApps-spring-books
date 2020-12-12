package com.cloudapps.springbooks.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudapps.springbooks.model.entity.User;
import com.cloudapps.springbooks.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository users;

	@Autowired
	public UserService() {

	}
	
	public Collection<User> findAll() {

		return users.findAll();
	}
	
	public Optional<User> findById(long id) {

		return users.findById(id);
	}
	
	public User save(User user) {

		return this.users.save(user);
	}
	
	public void deleteById(long id) {

		users.deleteById(id);
	}	
	
	public boolean isUserPresent(String nick) {
		
		return this.users.findByNick(nick).isEmpty() ? false : true;
	}

	public void replace(User updatedUser) {

		this.users.findById(updatedUser.getId()).orElseThrow();
		this.users.save(updatedUser);				
	}
	
	public void delete(User user) {
		this.users.delete(user);
	}
}
