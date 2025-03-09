package com.example.Learn.WaterDeliveryApp.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaystackService {

    @Value("${paystack.secret-key}")
    private String secretKey;

    private static final String PAYSTACK_INIT_URL = "https://api.paystack.co/transaction/initialize";

    public String initializePayment(String email, int amount) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> request = new HashMap<>();
        request.put("email", email);
        request.put("amount", amount * 100); // Convert to kobo (smallest currency unit)
        request.put("currency", "GHS");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + secretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(PAYSTACK_INIT_URL, entity, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            return (String) ((Map) responseBody.get("data")).get("authorization_url");
        }

        return null;
    }
}
