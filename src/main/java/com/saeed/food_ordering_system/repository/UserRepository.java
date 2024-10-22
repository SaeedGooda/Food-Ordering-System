package com.saeed.food_ordering_system.repository;

import com.saeed.food_ordering_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
