package com.parkauto.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkauto.rest.entity.Role;
import com.parkauto.rest.entity.User;


@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	User findByRole(Role role);
	User findByUsername(String username);
}
