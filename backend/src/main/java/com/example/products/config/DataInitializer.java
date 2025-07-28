package com.example.products.config;

import com.example.products.entity.Product;
import com.example.products.entity.User;
import com.example.products.repository.ProductRepository;
import com.example.products.repository.UserRepository;
import com.example.products.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserActivityRepository activityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
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

        productRepository.save(new Product("iPad Pro 12.9", 
            "Powerful tablet with M2 chip and Liquid Retina display", 
            new BigDecimal("1099.99"), "Electronics", 
            "https://images.unsplash.com/photo-1544244015-0df4b3ffc6b0?w=400"));

        productRepository.save(new Product("Nintendo Switch OLED", 
            "Gaming console with vibrant OLED screen", 
            new BigDecimal("349.99"), "Electronics", 
            "https://images.unsplash.com/photo-1606144042614-b2417e99c4e3?w=400"));

        productRepository.save(new Product("LG 27 4K Monitor", 
            "27-inch 4K UHD monitor with HDR support", 
            new BigDecimal("399.99"), "Electronics", 
            "https://images.unsplash.com/photo-1527443224154-c4a3942d3acf?w=400"));

        productRepository.save(new Product("Logitech MX Master 3S", 
            "Advanced wireless mouse for productivity", 
            new BigDecimal("99.99"), "Electronics", 
            "https://images.unsplash.com/photo-1527864550417-7fd91fc51a46?w=400"));

        productRepository.save(new Product("Canon EOS R6 Mark II", 
            "Full-frame mirrorless camera with 24.2MP sensor", 
            new BigDecimal("2499.99"), "Electronics", 
            "https://images.unsplash.com/photo-1606983340126-99ab4feaa64a?w=400"));

        productRepository.save(new Product("Bose SoundLink Revolve+", 
            "Portable Bluetooth speaker with 360-degree sound", 
            new BigDecimal("299.99"), "Electronics", 
            "https://images.unsplash.com/photo-1608043152269-423dbba4e7e1?w=400"));

        // Home & Kitchen Category (10 products)
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

        productRepository.save(new Product("All-Clad Stainless Steel Set", 
            "10-piece professional cookware set", 
            new BigDecimal("699.99"), "Home & Kitchen", 
            "https://images.unsplash.com/photo-1556909114-f6e7ad7d3136?w=400"));

        productRepository.save(new Product("Nest Learning Thermostat", 
            "Smart thermostat that learns your schedule", 
            new BigDecimal("249.99"), "Home & Kitchen", 
            "https://images.unsplash.com/photo-1545259741-2ea3ebf61fa0?w=400"));

        productRepository.save(new Product("Dyson V15 Detect", 
            "Cordless vacuum with laser dust detection", 
            new BigDecimal("749.99"), "Home & Kitchen", 
            "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=400"));

        productRepository.save(new Product("Le Creuset Dutch Oven", 
            "5.5-quart enameled cast iron Dutch oven", 
            new BigDecimal("349.99"), "Home & Kitchen", 
            "https://images.unsplash.com/photo-1556909114-f6e7ad7d3136?w=400"));

        // Sports & Fitness Category (8 products)
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

        productRepository.save(new Product("Optimum Nutrition Gold Standard", 
            "100% Whey Protein Powder - 5 lbs", 
            new BigDecimal("64.99"), "Sports & Fitness", 
            "https://images.unsplash.com/photo-1593095948071-474c5cc2989d?w=400"));

        productRepository.save(new Product("Peloton Bike+", 
            "Indoor exercise bike with rotating HD touchscreen", 
            new BigDecimal("2495.00"), "Sports & Fitness", 
            "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=400"));

        // Fashion & Clothing Category (8 products)
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

        productRepository.save(new Product("Uniqlo Heattech Ultra Warm Crew", 
            "Long sleeve thermal underwear for winter", 
            new BigDecimal("19.90"), "Fashion & Clothing", 
            "https://images.unsplash.com/photo-1602810318383-e386cc2a3ccf?w=400"));

        productRepository.save(new Product("Zara Wool Blend Coat", 
            "Elegant long coat in navy blue", 
            new BigDecimal("129.00"), "Fashion & Clothing", 
            "https://images.unsplash.com/photo-1544966503-7cc5ac882d5f?w=400"));

        productRepository.save(new Product("Allbirds Tree Runners", 
            "Sustainable sneakers made from eucalyptus tree fiber", 
            new BigDecimal("98.00"), "Fashion & Clothing", 
            "https://images.unsplash.com/photo-1549298916-b41d501d3772?w=400"));

        // Books & Education Category (6 products)
        productRepository.save(new Product("Clean Code by Robert Martin", 
            "A handbook of agile software craftsmanship", 
            new BigDecimal("42.99"), "Books & Education", 
            "https://images.unsplash.com/photo-1481627834876-b7833e8f5570?w=400"));

        productRepository.save(new Product("MasterClass All-Access Pass", 
            "Annual subscription to online classes by experts", 
            new BigDecimal("180.00"), "Books & Education", 
            "https://images.unsplash.com/photo-1522202176988-66273c2fd55f?w=400"));

        productRepository.save(new Product("Atomic Habits by James Clear", 
            "An easy and proven way to build good habits", 
            new BigDecimal("18.00"), "Books & Education", 
            "https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?w=400"));

        productRepository.save(new Product("Wacom Intuos Pro", 
            "Professional pen tablet for digital art", 
            new BigDecimal("379.95"), "Books & Education", 
            "https://images.unsplash.com/photo-1513475382585-d06e58bcb0e0?w=400"));

        productRepository.save(new Product("Coursera Plus Annual", 
            "Unlimited access to 7000+ courses from top universities", 
            new BigDecimal("399.00"), "Books & Education", 
            "https://images.unsplash.com/photo-1522202176988-66273c2fd55f?w=400"));

        productRepository.save(new Product("The Lean Startup by Eric Ries", 
            "How today's entrepreneurs use continuous innovation", 
            new BigDecimal("16.99"), "Books & Education", 
            "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400"));

        // Beauty & Personal Care Category (6 products)
        productRepository.save(new Product("The Ordinary Skincare Set", 
            "Complete skincare routine with serums and moisturizer", 
            new BigDecimal("89.90"), "Beauty & Personal Care", 
            "https://images.unsplash.com/photo-1556228720-195a672e8a03?w=400"));

        productRepository.save(new Product("Dyson Supersonic Hair Dryer", 
            "Professional hair dryer with intelligent heat control", 
            new BigDecimal("429.99"), "Beauty & Personal Care", 
            "https://images.unsplash.com/photo-1522338242992-e1a54906a8da?w=400"));

        productRepository.save(new Product("Urban Decay Naked Palette", 
            "Eyeshadow palette with 12 neutral shades", 
            new BigDecimal("54.00"), "Beauty & Personal Care", 
            "https://images.unsplash.com/photo-1512496015851-a90fb38ba796?w=400"));

        productRepository.save(new Product("Oral-B iO Series 9", 
            "Electric toothbrush with AI recognition", 
            new BigDecimal("299.99"), "Beauty & Personal Care", 
            "https://images.unsplash.com/photo-1607613009820-a29f7bb81c04?w=400"));

        productRepository.save(new Product("Chanel No. 5 Eau de Parfum", 
            "Iconic fragrance in 100ml bottle", 
            new BigDecimal("150.00"), "Beauty & Personal Care", 
            "https://images.unsplash.com/photo-1541643600914-78b084683601?w=400"));

        productRepository.save(new Product("Foreo Luna 3", 
            "Smart facial cleansing brush with app connectivity", 
            new BigDecimal("199.00"), "Beauty & Personal Care", 
            "https://images.unsplash.com/photo-1556228720-195a672e8a03?w=400"));

        // Additional categories to reach 50+ products
        
        // Automotive Category (4 products)
        productRepository.save(new Product("Tesla Model Y Floor Mats", 
            "All-weather floor mats designed for Tesla Model Y", 
            new BigDecimal("129.99"), "Automotive", 
            "https://images.unsplash.com/photo-1449824913935-59a10b8d2000?w=400"));

        productRepository.save(new Product("Garmin Dash Cam 67W", 
            "1440p dash camera with 180-degree field of view", 
            new BigDecimal("199.99"), "Automotive", 
            "https://images.unsplash.com/photo-1449824913935-59a10b8d2000?w=400"));

        productRepository.save(new Product("Chemical Guys Car Care Kit", 
            "Complete car washing and detailing kit", 
            new BigDecimal("89.99"), "Automotive", 
            "https://images.unsplash.com/photo-1449824913935-59a10b8d2000?w=400"));

        productRepository.save(new Product("Anker Roav Jump Starter", 
            "Portable car battery jump starter with USB ports", 
            new BigDecimal("79.99"), "Automotive", 
            "https://images.unsplash.com/photo-1449824913935-59a10b8d2000?w=400"));

        // Pet Supplies Category (4 products)
        productRepository.save(new Product("Blue Buffalo Life Protection", 
            "Natural dry dog food with real chicken", 
            new BigDecimal("54.99"), "Pet Supplies", 
            "https://images.unsplash.com/photo-1601758228041-f3b2795255f1?w=400"));

        productRepository.save(new Product("PetSafe ScoopFree Ultra", 
            "Self-cleaning litter box with covered hooded design", 
            new BigDecimal("169.95"), "Pet Supplies", 
            "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?w=400"));

        productRepository.save(new Product("Furbo Dog Camera", 
            "Interactive pet camera with treat dispenser", 
            new BigDecimal("199.00"), "Pet Supplies", 
            "https://images.unsplash.com/photo-1601758228041-f3b2795255f1?w=400"));

        productRepository.save(new Product("Petcube Bites 2", 
            "Smart pet camera with two-way audio and treats", 
            new BigDecimal("249.00"), "Pet Supplies", 
            "https://images.unsplash.com/photo-1601758228041-f3b2795255f1?w=400"));
    }
}