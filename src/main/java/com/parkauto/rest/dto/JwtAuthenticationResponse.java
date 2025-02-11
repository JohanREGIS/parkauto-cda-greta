package com.parkauto.rest.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

	private String token;
	private String refreshToken;
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private String profileImageURL;
	
}
