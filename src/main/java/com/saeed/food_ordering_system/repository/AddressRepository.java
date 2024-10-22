package com.saeed.food_ordering_system.repository;

import com.saeed.food_ordering_system.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
