package com.saeed.food_ordering_system.service;

import com.saeed.food_ordering_system.model.IngredientCategory;
import com.saeed.food_ordering_system.model.IngredientItem;
import com.saeed.food_ordering_system.model.Restaurant;
import com.saeed.food_ordering_system.repository.IngredientCategoryRepository;
import com.saeed.food_ordering_system.repository.IngredientItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsServiceImp implements IngredientsService{
    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setName(name);
        ingredientCategory.setRestaurant(restaurant);

        return ingredientCategoryRepository.save(ingredientCategory);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> ingredientCategory = ingredientCategoryRepository.findById(id);
        if(ingredientCategory.isEmpty()){
            throw new Exception("Ingredient Category Not Found");
        }
        return ingredientCategory.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory = findIngredientCategoryById(categoryId);

        IngredientItem ingredientItem = new IngredientItem();
        ingredientItem.setName(ingredientName);
        ingredientItem.setRestaurant(restaurant);
        ingredientItem.setCategory(ingredientCategory);

        IngredientItem ingredient = ingredientItemRepository.save(ingredientItem);
        ingredientCategory.getIngredients().add(ingredient);
        ingredientCategoryRepository.save(ingredientCategory);

        return ingredient;
    }

    @Override
    public List<IngredientItem> findRestaurantsIngredients(Long restaurantId) throws Exception {
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientItem updateStock(Long id) throws Exception {
        Optional<IngredientItem> optionalIngredientItem = ingredientItemRepository.findById(id);
        if(optionalIngredientItem.isEmpty()){
            throw new Exception("Ingredient Item Not Found");
        }
        IngredientItem ingredientItem = optionalIngredientItem.get();
        ingredientItem.setInStoke(!ingredientItem.isInStoke());
        return ingredientItemRepository.save(ingredientItem);
    }
}
