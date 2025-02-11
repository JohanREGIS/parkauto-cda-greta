package com.parkauto.rest.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.parkauto.rest.dto.JwtAuthenticationResponse;
import com.parkauto.rest.dto.RefreshTokenRequest;
import com.parkauto.rest.dto.SignInRequest;
import com.parkauto.rest.dto.SignUpRequest;
import com.parkauto.rest.entity.Role;
import com.parkauto.rest.entity.User;
import com.parkauto.rest.repository.IUserRepository;
import com.parkauto.rest.service.AuthenticationService;
import com.parkauto.rest.service.JWTService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	IUserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JWTService jwtService;
	
    @Override
	public User signUp(SignUpRequest signUpRequest) {
		User user = new User();
		user.setEmail(signUpRequest.getEmail());
		user.setFirstname(signUpRequest.getFirstname());
		user.setLastname(signUpRequest.getLastname());
		user.setRole(Role.ROLE_USER); // User is not allow to be an admin in register
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		
		return userRepository.save(user);
	}
	
	@Override
	public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
		
		var user = userRepository.findByEmail(signInRequest.getEmail());
		var jwt = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
		
		JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
		jwtAuthenticationResponse.setToken(jwt);
		jwtAuthenticationResponse.setRefreshToken(refreshToken);
		jwtAuthenticationResponse.setEmail(user.getEmail());
		jwtAuthenticationResponse.setFirstName(user.getFirstname());
		jwtAuthenticationResponse.setLastName(user.getLastname());
		jwtAuthenticationResponse.setProfileImageURL(user.getProfileImageURL());
		
		return jwtAuthenticationResponse;
	}
	
	@Override
	public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
		User user = userRepository.findByEmail(userEmail);
		
		if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
			var jwt = jwtService.generateToken(user);
			JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
			jwtAuthenticationResponse.setToken(jwt);
			jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
			
			return jwtAuthenticationResponse;
		}
		return null;
	}
}
