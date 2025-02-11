package com.parkauto.rest.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parkauto.rest.constant.UserImplConstant;
import com.parkauto.rest.entity.User;
import com.parkauto.rest.repository.IUserRepository;
import com.parkauto.rest.service.LoginAttemptService;
import com.parkauto.rest.service.UserService;


@Service
@Transactional
@Qualifier("UserDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {
	
	@Autowired
	IUserRepository userRepository;
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private LoginAttemptService loginAttemptService;
	
	//@Autowired
	public UserServiceImpl(IUserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetailsService userDetailsService() {
		
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
				return userRepository.findByEmail(username);
			}
		};
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			LOGGER.error(UserImplConstant.USER_NOT_FOUND_BY_USERNAME + username);
			throw new UsernameNotFoundException(UserImplConstant.USER_NOT_FOUND_BY_USERNAME + username);
		} else {
			validateLoginAttempt(user);
			user.setLastLoginDateDisplay(user.getLastLoginDate());
			user.setLastLoginDate(new Date());
			userRepository.save(user);
			
			return user;
		}
	}

	// Bloque un utilisateur si celui-ci a executé trop de tentatives de connexion avec un mauvais mot de passe
	private void validateLoginAttempt(User user) {
		if(user.isNotLocked()) {
			if(loginAttemptService.hasExceededMaxAttempts(user.getUsername())) {
				user.setNotLocked(false);
			} else {
				user.setNotLocked(true);
			}
		} else {
			loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
		}
		
	}
}
