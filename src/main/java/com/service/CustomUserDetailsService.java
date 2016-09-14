package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
		user.addRole("ROLE_USER");

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				true, true, user.isCredentialsNonExpired(), user.isAccountNonLocked(),
				getAuthorities(user));

	}

	/**
	 * Return the authorities.
	 *
	 * @param user
	 *            User
	 * @return List<GrantedAuthority>
	 */
	private List<GrantedAuthority> getAuthorities(User user) {
		Set<String> roles = user.getRoles();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

}
