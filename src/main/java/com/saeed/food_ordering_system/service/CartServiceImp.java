package com.saeed.food_ordering_system.service;

import com.saeed.food_ordering_system.model.Cart;
import com.saeed.food_ordering_system.model.CartItem;
import com.saeed.food_ordering_system.model.Food;
import com.saeed.food_ordering_system.model.User;
import com.saeed.food_ordering_system.repository.CartItemRepository;
import com.saeed.food_ordering_system.repository.CartRepository;
import com.saeed.food_ordering_system.request.AddCarItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImp implements CartService{
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;


    @Override
    public CartItem addItemToCart(AddCarItem req, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.findFoodById(req.getFoodId());
        Cart cart = cartRepository.findByCustomerId(user.getId());

        for(CartItem cartItem: cart.getItems()){
            if(cartItem.getFood().equals(food)){
                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setFood(food);
        item.setQuantity(req.getQuantity());
        item.setIngredients(req.getIngredients());
        item.setTotalPrice(req.getQuantity() * food.getPrice());

        CartItem savedItem = cartItemRepository.save(item);

        cart.getItems().add(savedItem);

        return savedItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw new Exception("Cart Item Id Not Found");
        }
        CartItem cartItem = cartItemOptional.get();
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(cartItem.getFood().getPrice() * quantity);

        return cartItemRepository.save(cartItem);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());

        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw new Exception("Cart Item Not Found");
        }
        CartItem cartItem = cartItemOptional.get();

        cart.getItems().remove(cartItem);

        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        Long total = 0L;
        for(CartItem item: cart.getItems()){
            total += (item.getFood().getPrice() * item.getQuantity());
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if(cartOptional.isEmpty()){
            throw new Exception("Cart Not Found");
        }
        return cartOptional.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        return cartRepository.findByCustomerId(userId);
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        Cart cart = findCartByUserId(userId);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}
