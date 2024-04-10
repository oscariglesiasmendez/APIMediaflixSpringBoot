package com.castelao.mediaflix_v4.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.castelao.mediaflix_v4.dto.OrderDetailDto;
import com.castelao.mediaflix_v4.entities.OrderDetail;

public class OrderDetailMapper {

	public static List<OrderDetailDto> toDtoList(List<OrderDetail> entities) {
		List<OrderDetailDto> dtos = new ArrayList<>();
        for (OrderDetail entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }

    public static OrderDetailDto toDto(OrderDetail entity) {
        OrderDetailDto dto = new OrderDetailDto();
        dto.setOrderId(entity.getOrder().getOrderId());
        dto.setProductId(entity.getProduct().getProductId());
        dto.setQuantity(entity.getQuantity());
        dto.setUnitPrice(entity.getUnitPrice());
        return dto;
    }

    public static OrderDetail toEntity(OrderDetailDto dto) {
        OrderDetail entity = new OrderDetail();
        entity.setQuantity(dto.getQuantity());
        entity.setUnitPrice(dto.getUnitPrice());
        // No es necesario mapear el producto ya que se espera que el DTO tenga el ID del producto
        return entity;
    }
    
//    public static Set<OrderDetail> toEntitySet(Set<OrderDetailDto> dtos) {
//        Set<OrderDetail> entities = new HashSet<>();
//        for (OrderDetailDto dto : dtos) {
//            entities.add(toEntity(dto));
//        }
//        return entities;
//    }
	
}