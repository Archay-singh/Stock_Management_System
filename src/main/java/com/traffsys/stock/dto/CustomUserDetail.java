package com.traffsys.stock.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.traffsys.stock.entity.User;

public class CustomUserDetail implements UserDetails {

	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	

	public CustomUserDetail(User user) {
		this.username = user.getUserName();
		this.password = user.getPassword();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

}
