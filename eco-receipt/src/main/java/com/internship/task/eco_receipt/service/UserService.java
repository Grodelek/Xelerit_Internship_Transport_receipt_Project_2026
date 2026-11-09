package com.internship.task.eco_receipt.service;

import com.internship.task.eco_receipt.dto.RegisterRequest;
import com.internship.task.eco_receipt.dto.UserResponse;
import com.internship.task.eco_receipt.entity.User;
import com.internship.task.eco_receipt.exception.BadRequestException;
import com.internship.task.eco_receipt.exception.ConflictException;
import com.internship.task.eco_receipt.exception.ResourceNotFoundException;
import com.internship.task.eco_receipt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public UserResponse register(RegisterRequest request) {
		if (request.password().getBytes(StandardCharsets.UTF_8).length > 72) {
			throw new BadRequestException("Password must be at most 72 bytes long");
		}
		String username = request.username();
		String email = request.email().toLowerCase(Locale.ROOT);
		if (userRepository.existsByUsername(username)) {
			throw new ConflictException("Username is already taken");
		}
		if (userRepository.existsByEmail(email)) {
			throw new ConflictException("Email address is already taken");
		}
		User user = userRepository.save(new User(username, email, passwordEncoder.encode(request.password())));
		return UserResponse.from(user);
	}

	@Transactional(readOnly = true)
	public UserResponse getProfile(String username) {
		return userRepository.findByUsername(username)
				.map(UserResponse::from)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}
}
