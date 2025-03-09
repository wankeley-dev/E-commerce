package com.example.Learn.WaterDeliveryApp.Controllers;

import com.example.Learn.WaterDeliveryApp.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout")
    public String checkout(@RequestParam String customerName, @RequestParam String email, @RequestParam String address) {
        orderService.placeOrder(customerName, email, address);
        return "redirect:/order/success"; // Redirect to success page
    }

    @GetMapping("/success")
    public String orderSuccess() {
        return "order_success"; // Create order_success.html in templates
    }

    @GetMapping("/details")
    public String orderDetails() {
        return "order_details"; // Make sure to create order_details.html in templates
    }

}

