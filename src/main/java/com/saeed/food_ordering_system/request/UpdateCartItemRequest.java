package com.saeed.food_ordering_system.request;

import lombok.Data;

@Data
public class UpdateCartItemRequest {
    private Long id;
    private int quantity;
}
