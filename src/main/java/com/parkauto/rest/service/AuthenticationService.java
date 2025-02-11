package com.parkauto.rest.service;

import com.parkauto.rest.dto.JwtAuthenticationResponse;
import com.parkauto.rest.dto.RefreshTokenRequest;
import com.parkauto.rest.dto.SignInRequest;
import com.parkauto.rest.dto.SignUpRequest;
import com.parkauto.rest.entity.User;

public interface AuthenticationService {
	
	User signUp(SignUpRequest signUpRequest);
	JwtAuthenticationResponse signIn(SignInRequest signInRequest);
	JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
