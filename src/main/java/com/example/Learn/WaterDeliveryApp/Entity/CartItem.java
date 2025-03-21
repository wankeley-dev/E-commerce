
package com.example.Learn.WaterDeliveryApp.Entity;

        import jakarta.persistence.*;
        import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Link to User instead of Order
    private User user; // Associate cart item with a specific user

    @ManyToOne
    @JoinColumn(name = "order_id") // This links CartItem to Order
    private Order order; // Add this field to fix the error
}

