package com.castelao.mediaflix_v4.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@NotNull
	@Column(name = "creation_date", insertable = true, updatable = false)
	private LocalDateTime creationDate;

	@NotNull
	private Double total;

	@NotNull
	@Column(name = "payment_method")
	private String paymentMethod;

	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	public Order(Long orderId, @NotNull LocalDateTime creationDate, @NotNull Double total,
			@NotNull String paymentMethod, Client client, OrderStatus status) {
		super();
		this.orderId = orderId;
		this.creationDate = creationDate;
		this.total = total;
		this.paymentMethod = paymentMethod;
		this.client = client;
		this.status = status;
	}

	public Order(@NotNull LocalDateTime creationDate, @NotNull Double total, @NotNull String paymentMethod,
			Client client, OrderStatus status) {
		super();
		this.creationDate = creationDate;
		this.total = total;
		this.paymentMethod = paymentMethod;
		this.client = client;
		this.status = status;
	}

//	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//	private Set<OrderDetail> orderDetails = new HashSet<>();



}
