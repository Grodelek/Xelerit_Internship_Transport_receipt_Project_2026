package com.internship.task.eco_receipt.dto;

import com.internship.task.eco_receipt.entity.User;

import java.time.Instant;

public record UserResponse(Long id, String username, String email, int totalXp, Instant createdAt) {

	public static UserResponse from(User user) {
		return new UserResponse(user.getId(), user.getUsername(), user.getEmail(),
				user.getTotalXp(), user.getCreatedAt());
	}
}
