package com.castelao.mediaflix_v4.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.castelao.mediaflix_v4.dto.OrderDto;
import com.castelao.mediaflix_v4.entities.Order;

public class OrderMapper {

	public static List<OrderDto> toDto(List<Order> entities) {
        return entities.stream()
                       .map(OrderMapper::toDto)
                       .collect(Collectors.toList());
    }

    public static OrderDto toDto(Order entity) {
        OrderDto dto = new OrderDto();
        dto.setOrderId(entity.getOrderId());
        dto.setCreationDate(entity.getCreationDate());
        dto.setTotal(entity.getTotal());
        dto.setPaymentMethod(entity.getPaymentMethod());
        // Map the client with its respective mappers if necessary
        dto.setClient(ClientMapper.toDto(entity.getClient()));
        dto.setStatus(entity.getStatus());
        // Map the order details
        //dto.setDetails(OrderDetailMapper.toDtoSet(entity.getOrderDetails()));
        return dto;
    }

    public static Order toEntity(OrderDto dto) {
        Order entity = new Order();
        entity.setOrderId(dto.getOrderId());
        entity.setCreationDate(dto.getCreationDate());
        entity.setTotal(dto.getTotal());
        entity.setPaymentMethod(dto.getPaymentMethod());
        // Map the client with its respective mappers if necessary
        entity.setClient(ClientMapper.toEntity(dto.getClient()));
        entity.setStatus(dto.getStatus());
        // Map the order details
        //entity.setOrderDetails(OrderDetailMapper.toEntitySet(dto.getDetails()));
        return entity;
    }
    
}