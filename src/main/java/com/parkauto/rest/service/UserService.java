package com.parkauto.rest.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.parkauto.rest.entity.User;

public interface UserService {
	
	UserDetailsService userDetailsService();
	User register(String firstName, String lastName, String username, String email);
	List<User> getUsers();
	User findUserByUsername(String username);
	User findUserByEmail(String email);
}
