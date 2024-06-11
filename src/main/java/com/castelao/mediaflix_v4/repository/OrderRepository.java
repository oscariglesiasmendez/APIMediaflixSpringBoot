package com.castelao.mediaflix_v4.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.castelao.mediaflix_v4.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT o FROM Order o WHERE o.creationDate >= :startDate ORDER BY o.creationDate DESC")
	List<Order> findLatestOrdersAfterDate(LocalDateTime startDate);

	@Query("SELECT o FROM Order o WHERE o.total >= :minPrice")
	List<Order> findOrdersWithTotalGreaterThan(BigDecimal minPrice);

	// Para obtener las últimas ventas
	@Query("SELECT o FROM Order o ORDER BY o.creationDate DESC")
	Page<Order> findLatestOrders(Pageable pageable);

	@Query("SELECT o FROM Order o WHERE o.creationDate = :date")
	List<Order> findByDate(@Param("date") LocalDateTime date);

	@Query("SELECT o FROM Order o WHERE o.client.clientId = :clientId")
    List<Order> findByClientId(@Param("clientId") Long clientId);
	
	@Query("SELECT o FROM Order o WHERE o.client.email = :email")
    List<Order> findByClientEmail(@Param("email") String email);
	
}
