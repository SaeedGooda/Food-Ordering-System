package com.saeed.food_ordering_system.repository;

import com.saeed.food_ordering_system.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByCustomerId(Long userId);
}
