package com.castelao.mediaflix_v4.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetail {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
     
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
     
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
	
	@NotNull
	private Integer quantity;

	@NotNull
	@Column(name = "unit_price")
	private Double unitPrice;

	public OrderDetail(Integer id, Order order, Product product, @NotNull Integer quantity,
			@NotNull Double unitPrice) {
		super();
		this.id = id;
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}	

}
