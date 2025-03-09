package com.example.Learn.WaterDeliveryApp.Services;

import com.example.Learn.WaterDeliveryApp.Entity.CartItem;
import com.example.Learn.WaterDeliveryApp.Entity.Order;
import com.example.Learn.WaterDeliveryApp.Repository.CartItemRepository;
import com.example.Learn.WaterDeliveryApp.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public Order placeOrder(String customerName, String email, String address) {
        List<CartItem> cartItems = cartItemRepository.findAll();

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty!");
        }

        Order order = new Order(cartItems, customerName, email, address, "PENDING");

        // Associate cart items with the new order
        for (CartItem cartItem : cartItems) {
            cartItem.setOrder(order);
        }

        return orderRepository.save(order); // Saves both Order & updates CartItems
    }
}
