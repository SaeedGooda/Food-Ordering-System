package com.saeed.food_ordering_system.service;

import com.saeed.food_ordering_system.model.Order;
import com.saeed.food_ordering_system.model.User;
import com.saeed.food_ordering_system.request.OrderRequest;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderRequest req, User user) throws Exception;
    Order updateOrder(Long orderId, String orderStatus) throws Exception;
    void cancelOrder(Long orderId) throws Exception;
    List<Order> getUsersOrder(Long userId) throws Exception;
    List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception;
    Order findOrderById(Long orderId) throws Exception;
}
