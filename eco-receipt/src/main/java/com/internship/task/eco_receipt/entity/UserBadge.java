package com.internship.task.eco_receipt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@Entity
@Table(name = "user_badges", uniqueConstraints = {
		@UniqueConstraint(name = "uk_user_badge", columnNames = {"user_id", "badge_id"})
})
public class UserBadge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "badge_id", nullable = false)
	private Badge badge;

	@Column(nullable = false, updatable = false)
	private Instant unlockedAt;

	protected UserBadge() {
	}

	public UserBadge(User user, Badge badge) {
		this.user = user;
		this.badge = badge;
	}

	@PrePersist
	void onCreate() {
		this.unlockedAt = Instant.now();
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Badge getBadge() {
		return badge;
	}

	public Instant getUnlockedAt() {
		return unlockedAt;
	}
}
