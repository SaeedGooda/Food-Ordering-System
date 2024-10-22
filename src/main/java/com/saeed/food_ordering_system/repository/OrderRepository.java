package com.saeed.food_ordering_system.repository;

import com.saeed.food_ordering_system.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long userId);
    List<Order> findByRestaurantId(Long restaurantId);
}
