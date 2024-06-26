package com.castelao.mediaflix_v4.dto;

import com.castelao.mediaflix_v4.entities.OrderDetail;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailDto {
	
	private Long orderId;
    private Long productId;
    private Integer quantity;
    private Double unitPrice;

    public OrderDetailDto(OrderDetail orderDetail) {
        this.orderId = orderDetail.getOrder().getOrderId();
        this.productId = orderDetail.getProduct().getProductId(); // Asumiendo que tienes un campo productId en la entidad Product
        this.quantity = orderDetail.getQuantity();
        this.unitPrice = orderDetail.getProduct().getPrice();
    }
	
}


/*
@Data
@NoArgsConstructor
public class OrderDetailDto {

	private Order orderId;
    private Product productId;
    private Integer quantity;
    private Double unitPrice;

    public OrderDetailDto(OrderDetail orderDetail) {
        this.orderId = orderDetail.getOrder();
        this.productId = orderDetail.getProduct(); // Asumiendo que tienes un campo productId en la entidad Product
        this.quantity = orderDetail.getQuantity();
        this.unitPrice = orderDetail.getProduct().getPrice();
    }
	
}
*/