package com.internship.task.eco_receipt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "user_challenges", uniqueConstraints = {
		@UniqueConstraint(name = "uk_user_challenge_date", columnNames = {"user_id", "challengeDate"})
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserChallenge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "challenge_id", nullable = false)
	private Challenge challenge;

	@NotNull
	@Column(nullable = false)
	private LocalDate challengeDate;

	@Min(0)
	@Column(nullable = false)
	private int progress;

	@Column(nullable = false)
	private boolean completed;

	private Instant completedAt;

	public UserChallenge(User user, Challenge challenge, LocalDate challengeDate) {
		this.user = user;
		this.challenge = challenge;
		this.challengeDate = challengeDate;
	}
}
