package com.saeed.food_ordering_system.controller;

import com.saeed.food_ordering_system.model.Food;
import com.saeed.food_ordering_system.service.FoodService;
import com.saeed.food_ordering_system.service.RestaurantService;
import com.saeed.food_ordering_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/foods")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(
            @RequestParam String keyword,
            @RequestHeader("Authorization") String jwt) throws Exception {
        List<Food> foods = foodService.searchFood(keyword);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @PathVariable Long restaurantId,
            @RequestParam boolean vegetarian,
            @RequestParam boolean seasonal,
            @RequestParam(required = false) String food_category,
            @RequestHeader("Authorization") String jwt) throws Exception {
        List<Food> foods = foodService.getRestaurantFoods(restaurantId, vegetarian, seasonal, food_category);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
