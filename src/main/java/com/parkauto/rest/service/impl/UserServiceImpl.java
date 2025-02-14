package com.parkauto.rest.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.parkauto.rest.constant.UserImplConstant;
import com.parkauto.rest.entity.Role;
import com.parkauto.rest.entity.User;
import com.parkauto.rest.exception.EmailExistException;
import com.parkauto.rest.exception.UserNotFoundException;
import com.parkauto.rest.exception.UsernameExistException;
import com.parkauto.rest.repository.IUserRepository;
import com.parkauto.rest.service.LoginAttemptService;
import com.parkauto.rest.service.UserService;


@Service
@Transactional
@Qualifier("UserDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {
	
	

	@Autowired
	IUserRepository userRepository;

	private BCryptPasswordEncoder passwordEncoder;
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private LoginAttemptService loginAttemptService;
	
	
	public UserServiceImpl(IUserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetailsService userDetailsService() {
		
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
				return userRepository.findByUsername(username);
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

	@Override
	public User register(String firstName, String lastName, String username, String email) {
		
		try {
			validateNewUsernameAndEmail(StringUtils.EMPTY, username, email);
			
			User user = new User();
			user.setUserId(generateUserId());
			String password = generatePassword();
			String encodedPassword = encodePassword(password);
			user.setFirstname(firstName);
			user.setLastname(lastName);
			user.setUsername(username);
			user.setEmail(email);
			user.setJoinDate(new Date());
			user.setPassword(encodedPassword);
			user.setActive(true);
			user.setNotLocked(true);
			user.setRole(Role.ROLE_USER);
			user.setAuthorities(Role.ROLE_USER.getAuthorities());
			user.setProfileImageURL(getTemporaryProfileImageUrl());
			userRepository.save(user);
			
			LOGGER.info(UserImplConstant.NEW_USER_PASSWORD + password);
			
			return user;
		} catch (UserNotFoundException | UsernameExistException | EmailExistException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	private String getTemporaryProfileImageUrl() {
		return ServletUriComponentsBuilder.fromCurrentContextPath().path(UserImplConstant.USER_IMAGE_PROFILE_TEMP).toUriString();
	}

	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

	private String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(12);
	}

	private String generateUserId() {
		return RandomStringUtils.randomNumeric(10);
	}

	// Vérifie que le username et l'email ne sont pas déjà utilisés
	private User validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws UserNotFoundException, UsernameExistException, EmailExistException{
		User userByNewUsername = findUserByUsername(newUsername);
		
		User userByNewEmail = findUserByEmail(newEmail);
		
		if(StringUtils.isNotBlank(currentUsername)) {
			User currentUser = findUserByUsername(currentUsername);
		
			if(currentUser == null) {
				throw new UserNotFoundException(UserImplConstant.NO_USER_FOUND_BY_USERNAME + currentUsername);
			}
			
			User userByUsername = findUserByUsername(newUsername);
			if(userByUsername != null && !currentUser.getId().equals(userByUsername.getId())) {
				throw new UsernameExistException(UserImplConstant.USERNAME_ALREADY_EXISTS);
			}
			
			User userByEmail = findUserByEmail(newEmail);
			if(userByEmail != null && !currentUser.getId().equals(userByEmail.getId())) {
				throw new EmailExistException(UserImplConstant.EMAIL_ALREADY_EXISTS);
			}
				return currentUser;
		} else {
			if(userByNewUsername != null) {
				throw new UsernameExistException(UserImplConstant.USERNAME_ALREADY_EXISTS + " " + userByNewUsername);
			}
		
			if(userByNewEmail != null) {
				throw new EmailExistException(UserImplConstant.EMAIL_ALREADY_EXISTS + " " + currentUsername + userByNewEmail);
			}
			return null;
		}
		
	}

	@Override
	public List<User> getUsers() {
		userRepository.findAll();
		return null;
	}

	@Override
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
