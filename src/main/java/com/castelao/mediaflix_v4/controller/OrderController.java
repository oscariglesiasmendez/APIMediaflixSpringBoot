package com.castelao.mediaflix_v4.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.castelao.mediaflix_v4.dto.BookDto;
import com.castelao.mediaflix_v4.dto.ClientDto;
import com.castelao.mediaflix_v4.dto.OrderDetailDto;
import com.castelao.mediaflix_v4.dto.OrderDto;
import com.castelao.mediaflix_v4.entities.Book;
import com.castelao.mediaflix_v4.entities.Client;
import com.castelao.mediaflix_v4.entities.Order;
import com.castelao.mediaflix_v4.entities.OrderDetail;
import com.castelao.mediaflix_v4.entities.OrderStatus;
import com.castelao.mediaflix_v4.entities.Product;
import com.castelao.mediaflix_v4.mapper.BookMapper;
import com.castelao.mediaflix_v4.mapper.ClientMapper;
import com.castelao.mediaflix_v4.mapper.OrderDetailMapper;
import com.castelao.mediaflix_v4.mapper.OrderMapper;
import com.castelao.mediaflix_v4.repository.OrderDetailRepository;
import com.castelao.mediaflix_v4.repository.OrderRepository;
import com.castelao.mediaflix_v4.repository.ProductRepository;
import com.castelao.mediaflix_v4.service.ClientService;
import com.castelao.mediaflix_v4.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private ClientService clientService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Operation(summary = "Get all orders")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Orders found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class)) }) })
	@GetMapping
	public List<OrderDto> findAll() {
	    List<Order> orders = orderService.getAllOrders();

	    List<OrderDto> dtos = OrderMapper.toDto(orders);
	    
	    //Recorro asi, porque si no siempre queda la última cantidad en todos
	    for (int i = 0; i < orders.size(); i++) {
	        Order o = orders.get(i);
	        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(o.getOrderId());
	        dtos.get(i).setDetails(OrderDetailMapper.toDtoList(orderDetails));
	    }
	    
	    return dtos;
	    
	}

	@Operation(summary = "Get the latest orders")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Orders found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class)) }) })
	@GetMapping("/latest")
	public List<OrderDto> findLatestOrders() {
		return orderService.findLatestOrdersWithDetails();
	}

	@Operation(summary = "Create an order")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Order created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class)) }),
			@ApiResponse(responseCode = "403", description = "Quantity of product greater than available", content = @Content),
			@ApiResponse(responseCode = "500", description = "Unprocessable Entity", content = @Content) })
	@PostMapping("/{clientId}")
	public ResponseEntity<OrderDto> createOrder(@PathVariable Long clientId, @RequestBody OrderDto orderDto) {
		System.out.println("Controller: \n clientID: " + clientId + "\n" + orderDto);

		List<OrderDetailDto> orderDetails = orderDto.getDetails();
		Product p = null;
		if (orderDetails != null) {
			for (OrderDetailDto o : orderDetails) {
				p = productRepository.getReferenceById(o.getProductId());
				if (p.getStock() < o.getQuantity() || !p.getAvailable()) {
					return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
				}
			}
		}

		// Buscar el cliente por su ID
		Client client = clientService.getById(clientId);

		// Convertir el cliente a DTO
		ClientDto clientDto = ClientMapper.toDto(client);

		// Asociar el cliente encontrado a la orden
		orderDto.setClient(clientDto);

		// Crear la orden a partir del DTO recibido
		OrderDto createdOrderDto = orderService.createOrder(orderDto);

		OrderDetail orderDetailTmp = null;

		Double price = 0D;
		Double totalPrice = 0D;

		Order order = OrderMapper.toEntity(createdOrderDto);

		for (OrderDetailDto o : orderDetails) {
			orderDetailTmp = OrderDetailMapper.toEntity(o);

			p = productRepository.getReferenceById(o.getProductId());

			p.setStock(p.getStock() - orderDetailTmp.getQuantity());

			if (p.getStock() == 0) {
				p.setAvailable(false);
			}

			orderDetailTmp.setProduct(p);
			price = p.getPrice();
			orderDetailTmp.setUnitPrice(price);
			// Accumulate price to the total price
			totalPrice += price * orderDetailTmp.getQuantity();

			orderDetailTmp.setOrder(order);

			orderDetailRepository.save(orderDetailTmp);

		}

		order.setTotal(totalPrice);
		orderRepository.save(order);

		// Verificar si la orden se creó correctamente
		if (createdOrderDto != null) {
			// Devolver una respuesta de éxito con el DTO de la orden creada
			return new ResponseEntity<>(createdOrderDto, HttpStatus.CREATED);
		} else {
			// Si hubo un error al crear la orden, devolver una respuesta de error
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@Operation(summary = "Update an order by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Order updated", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Order not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") Long orderId, @Valid @RequestBody OrderDto orderDto) {

		// Obtenemos la order por id para posteriormente setearle los campos que son
		// modificables (paymentMethod, status)

		Optional<Order> optionalOrder = orderRepository.findById(orderId);

		if (!optionalOrder.isPresent()) {
			return responseNotFound(orderId);
		}

		List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);

		Order existentOrder = optionalOrder.get();

		String paymentMethod = orderDto.getPaymentMethod();
		OrderStatus status = orderDto.getStatus();

		Product p = null;

		if (status.equals(OrderStatus.CANCELED)) {

			for (OrderDetail o : orderDetails) {

				p = o.getProduct();

				p.setStock(p.getStock() + o.getQuantity());

				orderDetailRepository.save(o);

			}

		}

		existentOrder.setPaymentMethod(paymentMethod);
		existentOrder.setStatus(status);

		Optional<OrderDto> orderDtoUpdated = orderService.update(orderId, OrderMapper.toDto(existentOrder));
		if (orderDtoUpdated.isPresent()) {
			return ResponseEntity.ok(orderDtoUpdated);
		} else {
			return responseNotFound(orderId);
		}
	}
	
	@Operation(summary = "Get all orders by clientId")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Orders found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OrderDto.class))) }) })
	@GetMapping("/client/{clientId}")
    public List<Order> getOrdersByClientId(@PathVariable Long clientId) {
        return orderService.getOrdersByClientId(clientId);
    }

	
	@Operation(summary = "Get all orders with %date%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Orders found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OrderDto.class))) }) })
	@GetMapping(value = "/by-date")
	public List<OrderDto> searchByDate(@RequestParam(name = "date") LocalDateTime date) {
		List<OrderDto> dtos = orderService.findLatestOrdersAfterDate(date);
		List<OrderDetail> orderDetails;

		for (OrderDto o : dtos) {
			orderDetails = orderDetailRepository.findByOrderId(o.getOrderId());

			for (OrderDto dto : dtos) {
				dto.setDetails(OrderDetailMapper.toDtoList(orderDetails));
			}
		}

		return dtos;
	}
	
	
	@Operation(summary = "Get all orders by date")
	  @ApiResponses(value = {
	      @ApiResponse(responseCode = "200", description = "Orders found", content = {
	          @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))
	      })
	  })
	  @GetMapping("/date")
	  public List<OrderDto> findOrdersByDate(@RequestParam(name = "date") LocalDateTime date) {
	    List<Order> orders = orderService.findOrdersByDate(date);
	    List<OrderDto> dtos = OrderMapper.toDto(orders);
	    return dtos;
	  }
	

	@Operation(summary = "Get all orders with %minPrice%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Orders found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OrderDto.class))) }) })
	@GetMapping(value = "/minPrice")
	public List<OrderDto> searchByTotalPriceGreaterThan(@RequestParam(name = "minPrice") BigDecimal minPrice) {
		List<OrderDto> dtos = orderService.findOrdersWithTotalGreaterThan(minPrice);

		List<OrderDetail> orderDetails;

		for (OrderDto o : dtos) {
			orderDetails = orderDetailRepository.findByOrderId(o.getOrderId());

			for (OrderDto dto : dtos) {
				dto.setDetails(OrderDetailMapper.toDtoList(orderDetails));
			}
		}

		return dtos;
	}

	private ResponseEntity<?> responseNotFound(Long libroId) {
		String errorMessage = "Order with id '" + libroId + "' not found";
		return responseNotFound(errorMessage);
	}

	private ResponseEntity<?> responseNotFound(String message) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(message));
	}

}
