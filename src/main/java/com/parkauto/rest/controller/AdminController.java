package com.parkauto.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdminController {

	@GetMapping("/auth/admin")
	public ResponseEntity<String> sayHello() {
		return  ResponseEntity.ok("Hello Admin");
	}
	
}
