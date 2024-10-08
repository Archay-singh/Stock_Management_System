package com.traffsys.stock.Service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.traffsys.stock.Repository.UserRepository;
import com.traffsys.stock.dto.CustomUserDetail;
import com.traffsys.stock.entity.User;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository credentialRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> credential = credentialRepo.findByUserName(username);
		return credential.map(CustomUserDetail::new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));
		
	}
	
}



















/*
 * 
 *    map(CustomUserDetail::new):
	If a user is found, it creates a new CustomUserDetail object (which implements UserDetails) 
	using the retrieved user information.


 * 
 * */
