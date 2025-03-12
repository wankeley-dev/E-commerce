package com.example.Learn.WaterDeliveryApp.Services;

import com.example.Learn.WaterDeliveryApp.Entity.CartItem;
import com.example.Learn.WaterDeliveryApp.Entity.Product;
import com.example.Learn.WaterDeliveryApp.Entity.User;
import com.example.Learn.WaterDeliveryApp.Repository.CartItemRepository;
import com.example.Learn.WaterDeliveryApp.Repository.ProductRepository;
import com.example.Learn.WaterDeliveryApp.Repository.UserRepository; // Add this
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository; // Inject UserRepository

    // Get the currently authenticated user by email/username
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            throw new RuntimeException("User not authenticated");
        }
        // Get the username (email in your case) from the authenticated principal
        String email = authentication.getName(); // Assumes email is the username in Spring Security
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found in database"));
    }

    public List<CartItem> getCartItems() {
        User user = getCurrentUser();
        return cartItemRepository.findByUser(user);
    }

    public void addToCart(Long productId, int quantity) {
        User user = getCurrentUser();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setUser(user);
        cartItemRepository.save(cartItem);
    }

    public void removeFromCart(Long cartItemId) {
        User user = getCurrentUser();
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        if (!cartItem.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Cannot remove item from another user's cart");
        }
        cartItemRepository.deleteById(cartItemId);
    }

    public double getTotalPrice() {
        User user = getCurrentUser();
        return cartItemRepository.findByUser(user).stream()
                .mapToDouble(cartItem -> cartItem.getProduct().getPrice() * cartItem.getQuantity())
                .sum();
    }
}