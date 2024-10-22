package com.saeed.food_ordering_system.service;

import com.saeed.food_ordering_system.model.Cart;
import com.saeed.food_ordering_system.model.CartItem;
import com.saeed.food_ordering_system.request.AddCarItem;

public interface CartService {
    CartItem addItemToCart(AddCarItem req, String jwt) throws Exception;
    CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;
    Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;
    Long calculateCartTotals(Cart cart) throws Exception;
    Cart findCartById(Long id) throws Exception;
    Cart findCartByUserId(Long userId) throws Exception;
    Cart clearCart(Long userId) throws Exception;
}
