package com.saeed.food_ordering_system.service;


import com.saeed.food_ordering_system.model.Category;
import com.saeed.food_ordering_system.model.Food;
import com.saeed.food_ordering_system.model.Restaurant;
import com.saeed.food_ordering_system.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {
    Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);
    void deleteFood(Long foodId) throws Exception;
    List<Food> getRestaurantFoods(Long restaurantId, boolean isVegetarian, boolean isSeasonal, String foodCategory) throws Exception;
    List<Food> searchFood(String keyword);
    Food findFoodById(Long foodId) throws Exception;
    Food updateAvailabilityStatus(Long foodId) throws Exception;
}
