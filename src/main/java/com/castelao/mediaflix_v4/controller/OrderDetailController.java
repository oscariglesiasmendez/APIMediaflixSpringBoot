package com.castelao.mediaflix_v4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.castelao.mediaflix_v4.dto.OrderDto;
import com.castelao.mediaflix_v4.entities.OrderDetail;
import com.castelao.mediaflix_v4.service.OrderDetailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailController {
	
	@Autowired
	private  OrderDetailService orderDetailService;

	@Operation(summary = "Get all order details by order ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order details found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Order details not found")
    })
    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderDetail>> getOrderDetailsByOrderId(@PathVariable Long orderId) {
        List<OrderDetail> orderDetails = orderDetailService.findByOrderId(orderId);
        if (orderDetails.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderDetails);
    }
}