package com.example.Learn.WaterDeliveryApp.Controllers;

import com.example.Learn.WaterDeliveryApp.Services.PaystackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaystackService paystackService;

    @PostMapping("/checkout")
    public String checkout(@RequestParam String email, @RequestParam int amount) {
        String paymentUrl = paystackService.initializePayment(email, amount);
        return "redirect:" + paymentUrl; // Redirect to Paystack payment page
    }
}
