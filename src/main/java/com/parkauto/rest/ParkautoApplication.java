package com.parkauto.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.parkauto.rest.entity.Role;
import com.parkauto.rest.entity.User;
import com.parkauto.rest.repository.IUserRepository;

@SpringBootApplication
public class ParkautoApplication implements CommandLineRunner {

	@Autowired
	private IUserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ParkautoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User adminAccount = userRepository.findByRole(Role.ROLE_ADMIN);
		if(adminAccount == null) {
			User user = new User();
			user.setEmail("admin@example.com");
			user.setFirstname("admin");
			user.setLastname("Admin");
			user.setRole(Role.ROLE_ADMIN);
			user.setActive(true);
			user.setNotLocked(true);
			user.setUsername("admin");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			
			userRepository.save(user);
		}
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
