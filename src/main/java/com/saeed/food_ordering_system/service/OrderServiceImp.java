package com.saeed.food_ordering_system.service;

import com.saeed.food_ordering_system.model.*;
import com.saeed.food_ordering_system.repository.*;
import com.saeed.food_ordering_system.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderRequest req, User user) throws Exception {
        Address deliveryAddress = req.getDeliveryAddress();
        Address savedAddress = addressRepository.save(deliveryAddress);

        if(!user.getAddresses().contains(savedAddress)){
            user.getAddresses().add(savedAddress);
            userRepository.save(user);
        }

        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());

        Cart cart = cartService.findCartByUserId(user.getId());

        Order order = new Order();
        order.setCustomer(user);
        order.setCreatedAt(new Date());
        order.setOrderStatus("PENDING");
        order.setRestaurant(restaurant);
        order.setDeliveryAddress(savedAddress);
        order.setRestaurant(restaurant);

        List<OrderItem> orderItems = new ArrayList<>();

        for(CartItem item: cart.getItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(item.getFood());
            orderItem.setIngredients(item.getIngredients());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setTotalPrice(item.getTotalPrice());

            OrderItem savedOrderItem = orderItemRepository.save(orderItem);

            orderItems.add(savedOrderItem);
        }

        order.setItems(orderItems);
        order.setTotalPrice(cart.getTotal());

        Order savedOrder = orderRepository.save(order);
        restaurant.getOrders().add(savedOrder);
        restaurantRepository.save(restaurant);

        user.getOrders().add(savedOrder);
        userRepository.save(user);

        return savedOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order = findOrderById(orderId);
        if(orderStatus.equals("PENDING")
            || orderStatus.equals("COMPLETED")
            || orderStatus.equals("DELIVERED")
            || orderStatus.equals("OUT_FOR_DELIVERY")){
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("Please Select a Valid Order Status");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        orderRepository.deleteById(order.getId());
    }

    @Override
    public List<Order> getUsersOrder(Long userId) throws Exception {
        return orderRepository.findByCustomerId(userId);
    }

    @Override
    public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
        List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
        if(orderStatus != null){
            orders = orders.stream().filter(order->
                    order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }
        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if(orderOptional.isEmpty()){
            throw new Exception("Order Not Found");
        }
        return orderOptional.get();
    }
}
