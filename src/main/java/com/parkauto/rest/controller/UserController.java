package com.parkauto.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkauto.rest.constant.SecurityConstant;
import com.parkauto.rest.constant.UserImplConstant;
import com.parkauto.rest.entity.User;
import com.parkauto.rest.exception.EmailExistException;
import com.parkauto.rest.exception.ExceptionHandling;
import com.parkauto.rest.exception.UserNotFoundException;
import com.parkauto.rest.exception.UsernameExistException;
import com.parkauto.rest.service.UserService;
import com.parkauto.rest.utility.JWTTokenProvider;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController extends ExceptionHandling {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTTokenProvider jwtTokenProvider;

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) throws UsernameExistException, EmailExistException {
		User newUser = userService.register(user.getFirstname(), user.getLastname(), user.getUsername(), user.getEmail());
		
		if(newUser != null) {
			return new ResponseEntity<>(newUser, HttpStatus.OK);
		}
		if(userService.findUserByUsername(user.getUsername()) != null) {
			throw new UsernameExistException(UserImplConstant.USERNAME_ALREADY_EXISTS);
		}
		if(userService.findUserByEmail(user.getEmail()) != null) {
			throw new EmailExistException(UserImplConstant.EMAIL_ALREADY_EXISTS);
		}
		
		return null;
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user) {
		
		authenticate(user.getUsername(), user.getPassword());
		
		User loginUser = userService.findUserByUsername(user.getUsername());
		HttpHeaders jwtHeaders = getJwtHeader(loginUser);
		
		return new ResponseEntity<>(loginUser, jwtHeaders, HttpStatus.OK);
	}

	private HttpHeaders getJwtHeader(User loginUser) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(SecurityConstant.JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(loginUser));
		
		return headers;
	}

	private void authenticate(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}
	
//	@GetMapping("/user")
//	public ResponseEntity<String> sayHello() {
//		return  ResponseEntity.ok("Hello User");
//	}
	
//	@GetMapping("/auth/home")
//	public String showUser() throws UserNotFoundException {
//		//return  ResponseEntity.ok("User page works !");
//		throw new UserNotFoundException("This username is not found.");
//	}
	
	
}
