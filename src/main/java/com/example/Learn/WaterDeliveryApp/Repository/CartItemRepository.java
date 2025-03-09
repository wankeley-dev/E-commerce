package com.example.Learn.WaterDeliveryApp.Repository;


import com.example.Learn.WaterDeliveryApp.Entity.CartItem;
import com.example.Learn.WaterDeliveryApp.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByOrder(Order order); // Get all items for a specific order

}
