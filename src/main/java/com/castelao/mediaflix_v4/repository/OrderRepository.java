package com.castelao.mediaflix_v4.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.castelao.mediaflix_v4.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	@Query("SELECT o FROM Order o WHERE o.creationDate >= :startDate ORDER BY o.creationDate DESC")
    List<Order> findLatestOrdersAfterDate(LocalDateTime startDate);
	
	
	@Query("SELECT o FROM Order o WHERE o.total >= :minPrice")
	List<Order> findOrdersWithTotalGreaterThan(BigDecimal minPrice);
	
}
