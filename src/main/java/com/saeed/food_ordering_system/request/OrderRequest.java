package com.saeed.food_ordering_system.request;

import com.saeed.food_ordering_system.model.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Address deliveryAddress;
}
