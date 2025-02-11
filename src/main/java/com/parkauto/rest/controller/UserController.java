package com.parkauto.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkauto.rest.exception.ExceptionHandling;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController extends ExceptionHandling {

	@GetMapping("/user")
	public ResponseEntity<String> sayHello() {
		return  ResponseEntity.ok("Hello User");
	}
	
	@GetMapping("/home")
	public ResponseEntity<String> showUser() {
		return  ResponseEntity.ok("User page works !");
	}
}
