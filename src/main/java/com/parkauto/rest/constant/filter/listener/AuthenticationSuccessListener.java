package com.parkauto.rest.constant.filter.listener;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.parkauto.rest.service.LoginAttemptService;

@Component
public class AuthenticationSuccessListener {
	
	@Autowired
	LoginAttemptService loginAttemptService;

	public AuthenticationSuccessListener() {
		super();
	}

	public AuthenticationSuccessListener(LoginAttemptService loginAttemptService) {
		super();
		this.loginAttemptService = loginAttemptService;
	}
	
	@EventListener
	public void onAuthenticationFailure(AuthenticationSuccessEvent event) {
		
		Object principal = event.getAuthentication().getPrincipal();
		
		if(principal instanceof User) {
			User user = (User) event.getAuthentication().getPrincipal();
			
			loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
		}
	}
}
