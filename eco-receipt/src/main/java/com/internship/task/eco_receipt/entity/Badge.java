package com.internship.task.eco_receipt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "badges")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Badge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false, unique = true)
	private String name;

	@NotBlank
	@Column(nullable = false, length = 500)
	private String description;

	@NotBlank
	@Column(nullable = false)
	private String icon;

	@Min(1)
	@Column(nullable = false)
	private int requiredValue;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	private BadgeType badgeType;

	public Badge(String name, String description, String icon, int requiredValue, BadgeType badgeType) {
		this.name = name;
		this.description = description;
		this.icon = icon;
		this.requiredValue = requiredValue;
		this.badgeType = badgeType;
	}
}
