package com.example.products.service;

import com.example.products.model.UserActivity;
import com.example.products.entity.User;
import com.example.products.entity.Product;
import com.example.products.repository.UserActivityRepository;
import com.example.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ActivityService {

    @Autowired
    private UserActivityRepository activityRepository;

    @Autowired
    private ProductRepository productRepository;

    // Track user activity
    public void trackActivity(User user, Long productId, UserActivity.ActivityType activityType) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            UserActivity activity = new UserActivity(user, product, activityType);
            activityRepository.save(activity);
        }
    }

    // Track anonymous user activity
    public void trackActivity(String sessionId, Long productId, UserActivity.ActivityType activityType) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            UserActivity activity = new UserActivity(sessionId, product, activityType);
            activityRepository.save(activity);
        }
    }

    // Get personalized recommendations for logged-in user
    public List<Product> getPersonalizedRecommendations(User user, int limit) {
        // Get recent activities (last 30 days)
        LocalDateTime since = LocalDateTime.now().minusDays(30);
        List<String> browsedCategories = activityRepository.findCategoriesBrowsedByUser(user, since);
        
        if (browsedCategories.isEmpty()) {
            // If no recent activity, return popular products
            return getPopularProducts(limit);
        }

        // Get products from browsed categories, excluding already viewed products
        List<Product> viewedProducts = activityRepository.findDistinctProductsViewedByUser(user);
        List<Long> viewedProductIds = viewedProducts.stream()
                .map(Product::getId)
                .collect(Collectors.toList());

        List<Product> recommendations = getProductsByCategoriesExcludingIds(browsedCategories, viewedProductIds);
        
        // If not enough recommendations, add popular products
        if (recommendations.size() < limit) {
            List<Product> popularProducts = getPopularProducts(limit - recommendations.size());
            for (Product popular : popularProducts) {
                if (!viewedProductIds.contains(popular.getId()) && !recommendations.contains(popular)) {
                    recommendations.add(popular);
                    if (recommendations.size() >= limit) break;
                }
            }
        }

        return recommendations.stream().limit(limit).collect(Collectors.toList());
    }

    // Get personalized recommendations for anonymous user
    public List<Product> getPersonalizedRecommendations(String sessionId, int limit) {
        // Get recent activities (last 7 days for anonymous users)
        LocalDateTime since = LocalDateTime.now().minusDays(7);
        List<String> browsedCategories = activityRepository.findCategoriesBrowsedBySession(sessionId, since);
        
        if (browsedCategories.isEmpty()) {
            // If no recent activity, return popular products
            return getPopularProducts(limit);
        }

        // Get products from browsed categories, excluding already viewed products
        List<Product> viewedProducts = activityRepository.findDistinctProductsViewedBySession(sessionId);
        List<Long> viewedProductIds = viewedProducts.stream()
                .map(Product::getId)
                .collect(Collectors.toList());

        List<Product> recommendations = getProductsByCategoriesExcludingIds(browsedCategories, viewedProductIds);
        
        // If not enough recommendations, add popular products
        if (recommendations.size() < limit) {
            List<Product> popularProducts = getPopularProducts(limit - recommendations.size());
            for (Product popular : popularProducts) {
                if (!viewedProductIds.contains(popular.getId()) && !recommendations.contains(popular)) {
                    recommendations.add(popular);
                    if (recommendations.size() >= limit) break;
                }
            }
        }

        return recommendations.stream().limit(limit).collect(Collectors.toList());
    }

    // Get "You may also like" recommendations based on recent activity
    public List<Product> getYouMayAlsoLikeRecommendations(User user, int limit) {
        List<Product> viewedProducts = activityRepository.findDistinctProductsViewedByUser(user);
        
        if (viewedProducts.isEmpty()) {
            return List.of(); // No recommendations for users with no activity
        }

        // Get categories from recently viewed products
        List<String> categories = viewedProducts.stream()
                .map(Product::getCategory)
                .distinct()
                .collect(Collectors.toList());

        // Get product IDs to exclude
        List<Long> excludeIds = viewedProducts.stream()
                .map(Product::getId)
                .collect(Collectors.toList());

        // Find products from same categories
        List<Product> recommendations = getProductsByCategoriesExcludingIds(categories, excludeIds);
        
        return recommendations.stream().limit(limit).collect(Collectors.toList());
    }

    // Get "You may also like" recommendations for anonymous user
    public List<Product> getYouMayAlsoLikeRecommendations(String sessionId, int limit) {
        List<Product> viewedProducts = activityRepository.findDistinctProductsViewedBySession(sessionId);
        
        if (viewedProducts.isEmpty()) {
            return List.of(); // No recommendations for users with no activity
        }

        // Get categories from recently viewed products
        List<String> categories = viewedProducts.stream()
                .map(Product::getCategory)
                .distinct()
                .collect(Collectors.toList());

        // Get product IDs to exclude
        List<Long> excludeIds = viewedProducts.stream()
                .map(Product::getId)
                .collect(Collectors.toList());

        // Find products from same categories
        List<Product> recommendations = getProductsByCategoriesExcludingIds(categories, excludeIds);
        
        return recommendations.stream().limit(limit).collect(Collectors.toList());
    }

    // Check if user has any activity
    public boolean hasUserActivity(User user) {
        return activityRepository.existsByUser(user);
    }

    // Check if session has any activity
    public boolean hasSessionActivity(String sessionId) {
        return activityRepository.existsBySessionId(sessionId);
    }

    // Get popular products (fallback for new users)
    private List<Product> getPopularProducts(int limit) {
        // For now, return random products. In a real system, this would be based on view counts, ratings, etc.
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream().limit(limit).collect(Collectors.toList());
    }

    // Helper method to get products by categories excluding specific IDs
    private List<Product> getProductsByCategoriesExcludingIds(List<String> categories, List<Long> excludeIds) {
        List<Product> allProducts = new ArrayList<>();
        for (String category : categories) {
            List<Product> categoryProducts = productRepository.findByCategory(category);
            allProducts.addAll(categoryProducts);
        }
        
        // Filter out excluded products
        return allProducts.stream()
                .filter(product -> !excludeIds.contains(product.getId()))
                .distinct()
                .collect(Collectors.toList());
    }
}