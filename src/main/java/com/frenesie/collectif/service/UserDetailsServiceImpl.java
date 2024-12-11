package com.frenesie.collectif.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.frenesie.collectif.model.User;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private UserService userService;
	
	public UserDetailsServiceImpl(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> userOpt = userService.findUserByEmail(username);
		
		UserDetails user = null;
		
		if (userOpt.isPresent()) {
			User userEntity = userOpt.get();
			user = org.springframework.security.core.userdetails.User.builder()
					.username(username)
					.password(userEntity.getPassword())
					.roles(userEntity.getRole()).build();
			return user;
		}
		throw new UsernameNotFoundException(username + " not found !");
	}
	
	
}
