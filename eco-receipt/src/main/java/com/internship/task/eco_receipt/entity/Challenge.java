package com.internship.task.eco_receipt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "challenges")
public class Challenge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false, unique = true)
	private String name;

	@NotBlank
	@Column(nullable = false, length = 500)
	private String description;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	private ChallengeType challengeType;

	@Min(1)
	@Column(nullable = false)
	private int targetValue;

	@Min(0)
	@Column(nullable = false)
	private int xpReward;

	protected Challenge() {
	}

	public Challenge(String name, String description, ChallengeType challengeType, int targetValue, int xpReward) {
		this.name = name;
		this.description = description;
		this.challengeType = challengeType;
		this.targetValue = targetValue;
		this.xpReward = xpReward;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public ChallengeType getChallengeType() {
		return challengeType;
	}

	public int getTargetValue() {
		return targetValue;
	}

	public int getXpReward() {
		return xpReward;
	}
}
