package com.parkauto.rest.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="user")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userId;	// ID de l'utilisateur qui sera visible sur l'application web
	
	@Column(name="FIRSTNAME")
	private String firstname;
	
	@Column(name="LASTNAME")
	private String lastname;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="ROLE")
	private Role role;	//ROLE_USER(read, edit), ROLE_ADMIN(create, read, edit, delete)

	@Column(name="PROFILEIMAGEURL")
	private String profileImageURL;
	
	private String[] authorities; // Tableau de String qui contient les permissions (read, update, delete ect)
	private boolean isActive; // Pour activer les rôles
	private boolean isNotLocked; // Pour bloquer ou non un user
	private Date lastLoginDate = new Date();
	private Date lastLoginDateDisplay = new Date();
	private Date joinDate = new Date();
	
	/*public User() {
		super();
	}
	
	public User(Long id, String firstname, String lastname, String email, String username, String password, Role role) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}*/

    @Override
	public String getUsername() {
		return username;
	}

	
    @Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return List.of(new SimpleGrantedAuthority(role.name()));
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
}
