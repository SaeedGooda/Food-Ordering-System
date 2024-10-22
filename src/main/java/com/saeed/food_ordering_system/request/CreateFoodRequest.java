package com.saeed.food_ordering_system.request;

import com.saeed.food_ordering_system.model.Category;
import com.saeed.food_ordering_system.model.IngredientItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;

    private Category category;
    private List<String> images;

    private Long restaurantId;
    private boolean vegetarian;
    private boolean seasonal;

    private List<IngredientItem> ingredients;
}
