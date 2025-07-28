package com.example.products.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/health")
@CrossOrigin(origins = "*")
public class HealthController {

    @GetMapping
    public Map<String, String> health() {
        return Map.of(
            "status", "UP",
            "message", "Application is running",
            "timestamp", java.time.LocalDateTime.now().toString()
        );
    }
}