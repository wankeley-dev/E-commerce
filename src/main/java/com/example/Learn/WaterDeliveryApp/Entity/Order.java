package com.example.Learn.WaterDeliveryApp.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders") // Renaming table to avoid SQL conflicts
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<CartItem> items;

    private String customerName;
    private String email;
    private String address;
    private String status; // "PENDING", "PAID", "SHIPPED"

    public Order() {}

    public Order(List<CartItem> items, String customerName, String email, String address, String status) {
        this.items = items;
        this.customerName = customerName;
        this.email = email;
        this.address = address;
        this.status = status;
    }

    // Getters and Setters
}
