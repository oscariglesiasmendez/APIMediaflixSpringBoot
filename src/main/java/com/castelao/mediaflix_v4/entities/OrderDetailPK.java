package com.castelao.mediaflix_v4.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class OrderDetailPK {

	@Column(name = "order_id")
	private Long orderId;
	
	@Column(name = "product_id")
	private Long productId;
	
}
