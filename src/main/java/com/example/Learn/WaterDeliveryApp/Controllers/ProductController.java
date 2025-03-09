package com.example.Learn.WaterDeliveryApp.Controllers;

import com.example.Learn.WaterDeliveryApp.Entity.Product;
import com.example.Learn.WaterDeliveryApp.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/products")  // Updated base path
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

    @GetMapping("/admin/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/dashboard";
    }

    @GetMapping("/user")
    public String listProductsForUser(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "user/products"; // User-facing product page
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product, @RequestParam("image") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // Ensure the directory exists
                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR + fileName);
                file.transferTo(filePath);
                product.setImageUrl("/images/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        productService.addProduct(product);
        return "redirect:/products/admin/dashboard";  // Redirect back to the admin panel
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products/admin/dashboard";  // Redirect back to the admin panel
    }
}
