package com.internship.task.eco_receipt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 3, max = 30)
	@Column(nullable = false, unique = true, length = 30)
	private String username;

	@NotBlank
	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@NotBlank
	@Column(nullable = false)
	private String password;

	@Min(0)
	@Column(nullable = false)
	private int totalXp;

	@Column(nullable = false, updatable = false)
	private Instant createdAt;

	protected User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	@PrePersist
	void onCreate() {
		this.createdAt = Instant.now();
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public int getTotalXp() {
		return totalXp;
	}

	public void addXp(int xp) {
		this.totalXp += xp;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}
}
