package com.example.products.controller;

import com.example.products.entity.Product;
import com.example.products.entity.User;
import com.example.products.model.UserActivity;
import com.example.products.service.ActivityService;
import com.example.products.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/recommendations")
@CrossOrigin(origins = "*")
public class RecommendationController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    // Track user activity
    @PostMapping("/track")
    public ResponseEntity<?> trackActivity(
            @RequestBody Map<String, Object> request,
            Authentication authentication,
            @RequestHeader(value = "Session-ID", required = false) String sessionId) {
        
        try {
            Long productId = Long.valueOf(request.get("productId").toString());
            String activityType = request.get("activityType").toString();
            
            UserActivity.ActivityType type = UserActivity.ActivityType.valueOf(activityType.toUpperCase());
            
            if (authentication != null && authentication.isAuthenticated()) {
                // Logged-in user
                String email = authentication.getName();
                User user = userService.findByEmail(email);
                if (user != null) {
                    activityService.trackActivity(user, productId, type);
                }
            } else {
                // Anonymous user
                if (sessionId == null || sessionId.isEmpty()) {
                    sessionId = UUID.randomUUID().toString();
                }
                activityService.trackActivity(sessionId, productId, type);
            }
            
            return ResponseEntity.ok().body(Map.of("success", true, "sessionId", sessionId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to track activity"));
        }
    }

    // Get personalized recommendations for home page
    @GetMapping("/personalized")
    public ResponseEntity<List<Product>> getPersonalizedRecommendations(
            @RequestParam(defaultValue = "8") int limit,
            Authentication authentication,
            @RequestHeader(value = "Session-ID", required = false) String sessionId) {
        
        List<Product> recommendations;
        
        if (authentication != null && authentication.isAuthenticated()) {
            // Logged-in user
            String email = authentication.getName();
            User user = userService.findByEmail(email);
            if (user != null) {
                recommendations = activityService.getPersonalizedRecommendations(user, limit);
            } else {
                recommendations = activityService.getPersonalizedRecommendations("", limit);
            }
        } else {
            // Anonymous user
            if (sessionId == null || sessionId.isEmpty()) {
                sessionId = UUID.randomUUID().toString();
            }
            recommendations = activityService.getPersonalizedRecommendations(sessionId, limit);
        }
        
        return ResponseEntity.ok(recommendations);
    }

    // Get "You may also like" recommendations
    @GetMapping("/you-may-also-like")
    public ResponseEntity<List<Product>> getYouMayAlsoLikeRecommendations(
            @RequestParam(defaultValue = "4") int limit,
            Authentication authentication,
            @RequestHeader(value = "Session-ID", required = false) String sessionId) {
        
        List<Product> recommendations;
        
        if (authentication != null && authentication.isAuthenticated()) {
            // Logged-in user
            String email = authentication.getName();
            User user = userService.findByEmail(email);
            if (user != null) {
                recommendations = activityService.getYouMayAlsoLikeRecommendations(user, limit);
            } else {
                recommendations = List.of();
            }
        } else {
            // Anonymous user
            if (sessionId == null || sessionId.isEmpty()) {
                recommendations = List.of();
            } else {
                recommendations = activityService.getYouMayAlsoLikeRecommendations(sessionId, limit);
            }
        }
        
        return ResponseEntity.ok(recommendations);
    }

    // Check if user has activity (to determine if we should show "You may also like")
    @GetMapping("/has-activity")
    public ResponseEntity<Map<String, Boolean>> hasUserActivity(
            Authentication authentication,
            @RequestHeader(value = "Session-ID", required = false) String sessionId) {
        
        boolean hasActivity = false;
        
        if (authentication != null && authentication.isAuthenticated()) {
            // Logged-in user
            String email = authentication.getName();
            User user = userService.findByEmail(email);
            if (user != null) {
                hasActivity = activityService.hasUserActivity(user);
            }
        } else {
            // Anonymous user
            if (sessionId != null && !sessionId.isEmpty()) {
                hasActivity = activityService.hasSessionActivity(sessionId);
            }
        }
        
        return ResponseEntity.ok(Map.of("hasActivity", hasActivity));
    }
}