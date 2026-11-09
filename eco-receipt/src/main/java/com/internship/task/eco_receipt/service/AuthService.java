package com.internship.task.eco_receipt.service;

import com.internship.task.eco_receipt.dto.LoginRequest;
import com.internship.task.eco_receipt.dto.UserResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	private final AuthenticationManager authenticationManager;
	private final UserService userService;

	public AuthService(AuthenticationManager authenticationManager, UserService userService) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
	}

	public Authentication authenticate(LoginRequest request) {
		return authenticationManager.authenticate(
				UsernamePasswordAuthenticationToken.unauthenticated(request.username(), request.password()));
	}

	public UserResponse profileOf(Authentication authentication) {
		return userService.getProfile(authentication.getName());
	}
}
