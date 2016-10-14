package com.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bean.User;

/**
 * Custom User Detail Service for Spring Security.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// Encoder for Passwords
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		// Dummy User: at this point you should recover this bean from your BBDD
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode("1234"));
		user.setEnabled(true);
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		return user;

	}

}
