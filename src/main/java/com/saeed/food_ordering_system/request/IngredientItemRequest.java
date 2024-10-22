package com.saeed.food_ordering_system.request;

import lombok.Data;

@Data
public class IngredientItemRequest {
    private String name;
    private Long categoryId;
    private Long restaurantId;
}
