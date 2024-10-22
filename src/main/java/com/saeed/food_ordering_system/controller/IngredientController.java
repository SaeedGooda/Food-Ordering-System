package com.saeed.food_ordering_system.controller;

import com.saeed.food_ordering_system.model.IngredientCategory;
import com.saeed.food_ordering_system.model.IngredientItem;
import com.saeed.food_ordering_system.model.Restaurant;
import com.saeed.food_ordering_system.model.User;
import com.saeed.food_ordering_system.request.CreateRestaurantRequest;
import com.saeed.food_ordering_system.request.IngredientCategoryRequest;
import com.saeed.food_ordering_system.request.IngredientItemRequest;
import com.saeed.food_ordering_system.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/ingredients")
public class IngredientController {
    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception {
        IngredientCategory ingredientCategory = ingredientsService.createIngredientCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(ingredientCategory, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<IngredientItem> createIngredientItem(
            @RequestBody IngredientItemRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception {
        IngredientItem ingredientItem = ingredientsService.createIngredientItem(req.getRestaurantId(), req.getName(), req.getCategoryId());
        return new ResponseEntity<>(ingredientItem, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientItem>> getRestaurantIngredient(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {
        List<IngredientItem> items = ingredientsService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {
        List<IngredientCategory> categories = ingredientsService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
