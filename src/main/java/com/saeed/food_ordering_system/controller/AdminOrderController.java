package com.saeed.food_ordering_system.controller;


import com.saeed.food_ordering_system.model.Order;
import com.saeed.food_ordering_system.model.User;
import com.saeed.food_ordering_system.request.OrderRequest;
import com.saeed.food_ordering_system.service.OrderService;
import com.saeed.food_ordering_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(
            @PathVariable Long id,
            @RequestParam(required = false) String order_status,
            @RequestHeader("Authorization") String jwt) throws Exception {
        List<Order> orders = orderService.getRestaurantsOrder(id, order_status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/order/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long orderId,
            @PathVariable String orderStatus,
            @RequestHeader("Authorization") String jwt) throws Exception {
        Order order = orderService.updateOrder(orderId, orderStatus);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
