package com.cloudapps.springbooks.services.impl;

import static java.util.Collections.emptyList;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cloudapps.springbooks.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<com.cloudapps.springbooks.models.User> user = userRepository.findByUsername(username);
		if (user.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
		else {
			return new User(user.get().getUsername(), user.get().getPassword(), emptyList());
		}
	}

}
