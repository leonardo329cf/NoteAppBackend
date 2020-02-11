package com.leonardocardozo.notesappbackend.security.details;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.leonardocardozo.notesappbackend.entities.User;

public class MyUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private boolean active;
	private List<GrantedAuthority> authorities = new ArrayList<>();
	
	public MyUserDetails(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.active = user.isActive();
		Arrays.stream(
				user.getRoles().split(","))
		.forEach(
				a -> authorities.add(
						new SimpleGrantedAuthority(a)));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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
		return active;
	}

}
