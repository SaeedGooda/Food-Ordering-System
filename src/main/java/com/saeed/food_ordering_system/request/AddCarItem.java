package com.saeed.food_ordering_system.request;

import lombok.Data;

import java.util.List;

@Data
public class AddCarItem {
    private Long foodId;
    private int quantity;
    private List<String> ingredients;
}
