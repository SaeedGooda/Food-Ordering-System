package com.saeed.food_ordering_system.repository;

import com.saeed.food_ordering_system.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
