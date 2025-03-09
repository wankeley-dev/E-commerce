package com.example.Learn.WaterDeliveryApp.Controllers;


import com.example.Learn.WaterDeliveryApp.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String checkoutPage() {
        return "checkout"; // Redirect to checkout page
    }

    @PostMapping("/place-order")
    public String placeOrder(@RequestParam String customerName,
                             @RequestParam String email,
                             @RequestParam String address) {
        orderService.placeOrder(customerName, email, address);
        return "redirect:/order-confirmation"; // Redirect to confirmation page
    }
}

