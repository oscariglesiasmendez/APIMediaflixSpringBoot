package com.castelao.mediaflix_v4.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.castelao.mediaflix_v4.dto.OrderDto;
import com.castelao.mediaflix_v4.entities.Order;
import com.castelao.mediaflix_v4.entities.OrderDetail;
import com.castelao.mediaflix_v4.mapper.OrderDetailMapper;
import com.castelao.mediaflix_v4.mapper.OrderMapper;
import com.castelao.mediaflix_v4.repository.ClientRepository;
import com.castelao.mediaflix_v4.repository.EmployeeRepository;
import com.castelao.mediaflix_v4.repository.OrderDetailRepository;
import com.castelao.mediaflix_v4.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

	private static Logger LOG = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Transactional
	public OrderDto createOrder(OrderDto orderDto) {

		Order order = OrderMapper.toEntity(orderDto);

		Order savedOrder = orderRepository.save(order);

		// Verificar si la orden se guardó correctamente
		if (savedOrder != null) {
			// Si se guardó correctamente, convertir la orden guardada a un DTO y devolverlo
			OrderDto dto = OrderMapper.toDto(savedOrder);
			dto.setDetails(orderDto.getDetails());
			return dto;
		} else {
			// Si hubo un error al guardar la orden, devolver null o manejar el error según
			// sea necesario
			return null;
		}
	}
	
	
	/**
	 * Obtiene todas las orders de ese cliente
	 * @param clientId
	 * @return List<Order>
	 */
	public List<Order> getOrdersByClientId(Long clientId) {
        return orderRepository.findByClientId(clientId);
    }

	/**
	 * Si el id del order recibido existe, actualiza el mismo con los campos
	 * recibidos en orderDto
	 * 
	 * Devuelve el order actualizado
	 * 
	 * Sino existe devuelve Optional.empty()
	 * 
	 * @param id           del order a buscar
	 * @param orderDetails objeto con todos los campos a sobreescribir en la entidad
	 * @return Optional<OrderDto>
	 */
	public Optional<OrderDto> update(Long id, OrderDto orderDto) {
		Optional<Order> optionalOrder = orderRepository.findById(id);
		if (optionalOrder.isPresent()) {
			Order order = optionalOrder.get();

			modelMapper.getConfiguration().setSkipNullEnabled(true).setSkipNullEnabled(true);
			// Copiar propiedades desde el objeto libro a la entidad
			modelMapper.map(orderDto, order);

			Order orderSaved = orderRepository.save(order);
			return Optional.of(OrderMapper.toDto(orderSaved));
		} else {
			LOG.info("order no encontrada: " + id);
			return Optional.empty();
		}
	}

	public List<OrderDto> findLatestOrdersAfterDate(LocalDateTime startDate) {
		List<OrderDto> dtos = new ArrayList<OrderDto>();
		List<Order> orders = orderRepository.findLatestOrdersAfterDate(startDate);

		if (orders != null) {
			dtos = OrderMapper.toDto(orders);
		}

		return dtos;
	}

	public List<OrderDto> findOrdersWithTotalGreaterThan(BigDecimal minPrice) {
		List<OrderDto> dtos = new ArrayList<OrderDto>();
		List<Order> orders = orderRepository.findOrdersWithTotalGreaterThan(minPrice);

		if (orders != null) {
			dtos = OrderMapper.toDto(orders);
		}

		return dtos;
	}

	public List<OrderDto> findLatestOrdersWithDetails() {
		Pageable pageable = PageRequest.of(0, 20, Sort.Direction.DESC, "creationDate");
		Page<Order> orderPage = orderRepository.findLatestOrders(pageable);
		List<OrderDto> dtos = new ArrayList<>();

		for (Order order : orderPage.getContent()) {
			List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(order.getOrderId());
			OrderDto dto = OrderMapper.toDto(order);
			dto.setDetails(OrderDetailMapper.toDtoList(orderDetails));
			dtos.add(dto);
		}

		return dtos;
	}

	public List<Order> findOrdersByDate(LocalDateTime fecha) {
		return orderRepository.findByDate(fecha);
	}

}