package com.internship.task.eco_receipt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "trips", indexes = {
		@Index(name = "idx_trips_user_completed", columnList = "user_id, completedAt")
})
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

	protected Trip() {
	}

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

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public String getStartLocation() {
		return startLocation;
	}

	public String getDestination() {
		return destination;
	}

	public TransportType getTransportType() {
		return transportType;
	}

	public BigDecimal getDistanceKm() {
		return distanceKm;
	}

	public int getDurationMinutes() {
		return durationMinutes;
	}

	public BigDecimal getEstimatedCost() {
		return estimatedCost;
	}

	public BigDecimal getCo2Emission() {
		return co2Emission;
	}

	public BigDecimal getCo2Saved() {
		return co2Saved;
	}

	public Instant getCompletedAt() {
		return completedAt;
	}
}
