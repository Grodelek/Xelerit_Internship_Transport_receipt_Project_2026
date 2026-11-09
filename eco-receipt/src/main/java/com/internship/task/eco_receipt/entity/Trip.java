package com.internship.task.eco_receipt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "trips", indexes = {
		@Index(name = "idx_trips_user_completed", columnList = "user_id, completedAt")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@NotBlank
	@Column(nullable = false)
	private String startLocation;

	@NotBlank
	@Column(nullable = false)
	private String destination;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private TransportType transportType;

	@NotNull
	@DecimalMin("0.0")
	@Column(nullable = false, precision = 10, scale = 3)
	private BigDecimal distanceKm;

	@Min(0)
	@Column(nullable = false)
	private int durationMinutes;

	@NotNull
	@DecimalMin("0.0")
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal estimatedCost;

	@NotNull
	@DecimalMin("0.0")
	@Column(nullable = false, precision = 10, scale = 3)
	private BigDecimal co2Emission;

	@NotNull
	@DecimalMin("0.0")
	@Column(nullable = false, precision = 10, scale = 3)
	private BigDecimal co2Saved;

	@Column(nullable = false)
	private Instant completedAt;

	public Trip(User user, String startLocation, String destination, TransportType transportType,
				BigDecimal distanceKm, int durationMinutes, BigDecimal estimatedCost,
				BigDecimal co2Emission, BigDecimal co2Saved) {
		this.user = user;
		this.startLocation = startLocation;
		this.destination = destination;
		this.transportType = transportType;
		this.distanceKm = distanceKm;
		this.durationMinutes = durationMinutes;
		this.estimatedCost = estimatedCost;
		this.co2Emission = co2Emission;
		this.co2Saved = co2Saved;
	}

	@PrePersist
	void onCreate() {
		if (completedAt == null) {
			completedAt = Instant.now();
		}
	}
}
