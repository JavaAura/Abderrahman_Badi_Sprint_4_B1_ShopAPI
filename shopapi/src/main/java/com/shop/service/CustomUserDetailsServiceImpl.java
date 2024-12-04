package com.shop.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shop.model.User;
import com.shop.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Service implementation for User entity.
 * Defines methods for CRUD operations and additional business logic.
 */
@Service
@Log4j2
@AllArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

		return new org.springframework.security.core.userdetails.User(
				user.getUserName(),
				user.getPassword(),
				user.isEnabled(),
				true, // accountNonExpired
				true, // credentialsNonExpired
				true, // accountNonLocked
				getAuthorities(user));

	}

	private Collection<? extends GrantedAuthority> getAuthorities(User user) {
		return Arrays.asList(new SimpleGrantedAuthority(user.getRole().getName()));
	}

}
