package com.castelao.mediaflix_v4.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.castelao.mediaflix_v4.entities.OrderStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {

	private Long orderId;
	private LocalDateTime creationDate;
	private Double total;
	private String paymentMethod;
	private ClientDto client;
	private OrderStatus status;
	private List<OrderDetailDto> details;

	public OrderDto(Long orderId, LocalDateTime creationDate, Double total, String paymentMethod, ClientDto client,
			OrderStatus status) {
		super();
		this.orderId = orderId;
		this.creationDate = creationDate;
		this.total = total;
		this.paymentMethod = paymentMethod;
		this.client = client;
		this.status = status;
	}

	public OrderDto(LocalDateTime creationDate, Double total, String paymentMethod, ClientDto client,
			OrderStatus status) {
		super();
		this.creationDate = creationDate;
		this.total = total;
		this.paymentMethod = paymentMethod;
		this.client = client;
		this.status = status;
	}

	public OrderDto(Long orderId, LocalDateTime creationDate, Double total, String paymentMethod, ClientDto client,
			OrderStatus status, List<OrderDetailDto> details) {
		super();
		this.orderId = orderId;
		this.creationDate = creationDate;
		this.total = total;
		this.paymentMethod = paymentMethod;
		this.client = client;
		this.status = status;
		this.details = details;
	}

	/*
	 * public OrderDto(Order order) { this.orderId = order.getOrderId();
	 * this.creationDate = order.getCreationDate(); this.total = order.getTotal();
	 * this.paymentMethod = order.getPaymentMethod(); this.client = new
	 * ClientDto(order.getClient()); this.status = order.getStatus(); this.details =
	 * order.getOrderDetails().stream() .map(OrderDetailDto::new)
	 * .collect(Collectors.toSet()); }
	 */
	

}
