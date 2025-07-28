package com.example.products.controller;

import com.example.products.entity.Product;
import com.example.products.entity.User;
import com.example.products.repository.ProductRepository;
import com.example.products.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
@CrossOrigin(origins = "*")
public class DataController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/initialize")
    public Map<String, Object> initializeData() {
        try {
            // Initialize users if they don't exist
            if (userRepository.count() == 0) {
                User admin = new User("Admin User", "admin@example.com", 
                    passwordEncoder.encode("password123"), User.Role.ADMIN);
                User customer1 = new User("John Doe", "john@example.com", 
                    passwordEncoder.encode("password123"), User.Role.CUSTOMER);
                User customer2 = new User("Jane Smith", "jane@example.com", 
                    passwordEncoder.encode("password123"), User.Role.CUSTOMER);

                userRepository.save(admin);
                userRepository.save(customer1);
                userRepository.save(customer2);
            }

            // Initialize products if they don't exist
            if (productRepository.count() == 0) {
                initializeProducts();
            }

            long productCount = productRepository.count();
            long userCount = userRepository.count();

            return Map.of(
                "success", true,
                "message", "Data initialized successfully",
                "productCount", productCount,
                "userCount", userCount
            );
        } catch (Exception e) {
            return Map.of(
                "success", false,
                "message", "Error initializing data: " + e.getMessage()
            );
        }
    }

    private void initializeProducts() {
        // Electronics Category (12 products)
        productRepository.save(new Product("MacBook Pro 16", 
            "Apple MacBook Pro with M3 Pro chip, 18GB RAM, 512GB SSD", 
            new BigDecimal("2499.99"), "Electronics", 
            "https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=400"));

        productRepository.save(new Product("Dell XPS 13", 
            "Ultra-thin laptop with Intel i7, 16GB RAM, 1TB SSD", 
            new BigDecimal("1299.99"), "Electronics", 
            "https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=400"));

        productRepository.save(new Product("Sony WH-1000XM5", 
            "Industry-leading noise canceling wireless headphones", 
            new BigDecimal("399.99"), "Electronics", 
            "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400"));

        productRepository.save(new Product("AirPods Pro 2", 
            "Apple AirPods Pro with active noise cancellation", 
            new BigDecimal("249.99"), "Electronics", 
            "https://images.unsplash.com/photo-1606220945770-b5b6c2c55bf1?w=400"));

        productRepository.save(new Product("iPhone 15 Pro Max", 
            "Latest iPhone with A17 Pro chip and titanium design", 
            new BigDecimal("1199.99"), "Electronics", 
            "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=400"));

        productRepository.save(new Product("Samsung Galaxy S24 Ultra", 
            "Android flagship with AI features and 200MP camera", 
            new BigDecimal("1299.99"), "Electronics", 
            "https://images.unsplash.com/photo-1610945265064-0e34e5519bbf?w=400"));

        // Home & Kitchen Category (6 products)
        productRepository.save(new Product("Breville Barista Express", 
            "Espresso machine with built-in grinder", 
            new BigDecimal("699.99"), "Home & Kitchen", 
            "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085?w=400"));

        productRepository.save(new Product("Ninja Foodi Air Fryer", 
            "8-quart air fryer with multiple cooking functions", 
            new BigDecimal("199.99"), "Home & Kitchen", 
            "https://images.unsplash.com/photo-1556909114-f6e7ad7d3136?w=400"));

        productRepository.save(new Product("KitchenAid Stand Mixer", 
            "Artisan series 5-quart stand mixer", 
            new BigDecimal("429.99"), "Home & Kitchen", 
            "https://images.unsplash.com/photo-1578662996442-48f60103fc96?w=400"));

        productRepository.save(new Product("Vitamix A3500", 
            "High-performance blender with smart technology", 
            new BigDecimal("549.99"), "Home & Kitchen", 
            "https://images.unsplash.com/photo-1570197788417-0e82375c9371?w=400"));

        productRepository.save(new Product("iRobot Roomba j7+", 
            "Smart robot vacuum with self-emptying base", 
            new BigDecimal("849.99"), "Home & Kitchen", 
            "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=400"));

        productRepository.save(new Product("Instant Pot Duo 7-in-1", 
            "Electric pressure cooker with multiple functions", 
            new BigDecimal("99.99"), "Home & Kitchen", 
            "https://images.unsplash.com/photo-1585515656973-c0e8e4b0e8b8?w=400"));

        // Sports & Fitness Category (6 products)
        productRepository.save(new Product("Nike Air Zoom Pegasus 40", 
            "Men's road running shoes with responsive cushioning", 
            new BigDecimal("129.99"), "Sports & Fitness", 
            "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=400"));

        productRepository.save(new Product("Adidas Ultraboost 23", 
            "Premium running shoes with BOOST midsole", 
            new BigDecimal("189.99"), "Sports & Fitness", 
            "https://images.unsplash.com/photo-1551107696-a4b0c5a0d9a2?w=400"));

        productRepository.save(new Product("Manduka PRO Yoga Mat", 
            "Professional-grade yoga mat with lifetime guarantee", 
            new BigDecimal("88.00"), "Sports & Fitness", 
            "https://images.unsplash.com/photo-1544367567-0f2fcb009e0b?w=400"));

        productRepository.save(new Product("Bowflex SelectTech 552", 
            "Adjustable dumbbells that replace 15 sets", 
            new BigDecimal("399.99"), "Sports & Fitness", 
            "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=400"));

        productRepository.save(new Product("Apple Watch Series 9", 
            "Advanced fitness tracker with GPS and cellular", 
            new BigDecimal("429.99"), "Sports & Fitness", 
            "https://images.unsplash.com/photo-1575311373937-040b8e1fd5b6?w=400"));

        productRepository.save(new Product("TRX Suspension Trainer", 
            "Complete bodyweight training system", 
            new BigDecimal("195.00"), "Sports & Fitness", 
            "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=400"));

        // Fashion & Clothing Category (6 products)
        productRepository.save(new Product("Levi's 501 Original Jeans", 
            "Classic straight-leg jeans in authentic indigo", 
            new BigDecimal("89.50"), "Fashion & Clothing", 
            "https://images.unsplash.com/photo-1542272604-787c3835535d?w=400"));

        productRepository.save(new Product("Canada Goose Expedition Parka", 
            "Extreme weather outerwear for harsh conditions", 
            new BigDecimal("1095.00"), "Fashion & Clothing", 
            "https://images.unsplash.com/photo-1544966503-7cc5ac882d5f?w=400"));

        productRepository.save(new Product("Patagonia Better Sweater", 
            "Fleece jacket made from recycled polyester", 
            new BigDecimal("99.00"), "Fashion & Clothing", 
            "https://images.unsplash.com/photo-1434389677669-e08b4cac3105?w=400"));

        productRepository.save(new Product("Ray-Ban Aviator Sunglasses", 
            "Classic aviator sunglasses with polarized lenses", 
            new BigDecimal("154.00"), "Fashion & Clothing", 
            "https://images.unsplash.com/photo-1572635196237-14b3f281503f?w=400"));

        productRepository.save(new Product("Nike Air Force 1", 
            "Iconic basketball shoes in white leather", 
            new BigDecimal("90.00"), "Fashion & Clothing", 
            "https://images.unsplash.com/photo-1549298916-b41d501d3772?w=400"));

        productRepository.save(new Product("Allbirds Tree Runners", 
            "Sustainable sneakers made from eucalyptus tree fiber", 
            new BigDecimal("98.00"), "Fashion & Clothing", 
            "https://images.unsplash.com/photo-1549298916-b41d501d3772?w=400"));
    }
}