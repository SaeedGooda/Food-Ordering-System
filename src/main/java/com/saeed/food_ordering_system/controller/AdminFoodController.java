package com.saeed.food_ordering_system.controller;

import com.saeed.food_ordering_system.model.Food;
import com.saeed.food_ordering_system.model.Restaurant;
import com.saeed.food_ordering_system.model.User;
import com.saeed.food_ordering_system.request.CreateFoodRequest;
import com.saeed.food_ordering_system.response.MessageResponse;
import com.saeed.food_ordering_system.service.FoodService;
import com.saeed.food_ordering_system.service.RestaurantService;
import com.saeed.food_ordering_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/foods")
public class AdminFoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping()
    public ResponseEntity<Food> createFood(
            @RequestBody CreateFoodRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
        Food food = foodService.createFood(req, req.getCategory(), restaurant);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {
        foodService.deleteFood(id);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Food Deleted Successfully");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailability(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {
        Food food = foodService.updateAvailabilityStatus(id);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }
}
