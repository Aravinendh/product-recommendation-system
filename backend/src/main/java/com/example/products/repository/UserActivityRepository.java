package com.example.products.repository;

import com.example.products.model.UserActivity;
import com.example.products.entity.User;
import com.example.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
    
    // Find recent activities for a user
    @Query("SELECT ua FROM UserActivity ua WHERE ua.user = :user ORDER BY ua.createdAt DESC")
    List<UserActivity> findByUserOrderByCreatedAtDesc(@Param("user") User user);

    // Find recent activities for a session (anonymous user)
    @Query("SELECT ua FROM UserActivity ua WHERE ua.sessionId = :sessionId ORDER BY ua.createdAt DESC")
    List<UserActivity> findBySessionIdOrderByCreatedAtDesc(@Param("sessionId") String sessionId);

    // Find recent activities for a user within time period
    @Query("SELECT ua FROM UserActivity ua WHERE ua.user = :user AND ua.createdAt >= :since ORDER BY ua.createdAt DESC")
    List<UserActivity> findByUserAndCreatedAtAfterOrderByCreatedAtDesc(@Param("user") User user, @Param("since") LocalDateTime since);

    // Find recent activities for a session within time period
    @Query("SELECT ua FROM UserActivity ua WHERE ua.sessionId = :sessionId AND ua.createdAt >= :since ORDER BY ua.createdAt DESC")
    List<UserActivity> findBySessionIdAndCreatedAtAfterOrderByCreatedAtDesc(@Param("sessionId") String sessionId, @Param("since") LocalDateTime since);

    // Get distinct products viewed by user
    @Query("SELECT ua.product FROM UserActivity ua WHERE ua.user = :user AND ua.activityType = 'VIEW' GROUP BY ua.product ORDER BY MAX(ua.createdAt) DESC")
    List<Product> findDistinctProductsViewedByUser(@Param("user") User user);

    // Get distinct products viewed by session
    @Query("SELECT ua.product FROM UserActivity ua WHERE ua.sessionId = :sessionId AND ua.activityType = 'VIEW' GROUP BY ua.product ORDER BY MAX(ua.createdAt) DESC")
    List<Product> findDistinctProductsViewedBySession(@Param("sessionId") String sessionId);

    // Get categories browsed by user
    @Query("SELECT DISTINCT ua.product.category FROM UserActivity ua WHERE ua.user = :user AND ua.createdAt >= :since")
    List<String> findCategoriesBrowsedByUser(@Param("user") User user, @Param("since") LocalDateTime since);

    // Get categories browsed by session
    @Query("SELECT DISTINCT ua.product.category FROM UserActivity ua WHERE ua.sessionId = :sessionId AND ua.createdAt >= :since")
    List<String> findCategoriesBrowsedBySession(@Param("sessionId") String sessionId, @Param("since") LocalDateTime since);

    // Check if user has any activities
    boolean existsByUser(User user);

    // Check if session has any activities
    boolean existsBySessionId(String sessionId);
}