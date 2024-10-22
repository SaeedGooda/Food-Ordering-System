package com.saeed.food_ordering_system.service;

import com.saeed.food_ordering_system.model.IngredientCategory;
import com.saeed.food_ordering_system.model.IngredientItem;

import java.util.List;

public interface IngredientsService {
    IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;
    IngredientCategory findIngredientCategoryById(Long id) throws Exception;
    List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;
    IngredientItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception;
    List<IngredientItem> findRestaurantsIngredients(Long restaurantId) throws Exception;
    IngredientItem updateStock(Long id) throws Exception;
}
