package com.saeed.food_ordering_system.repository;

import com.saeed.food_ordering_system.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
