package com.fintex.interview.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Health endpoint to verify application and database configuration.
 * Returns "up" if DB connection works and schema is accessible, "down" otherwise.
 */
@RestController
@RequestMapping("/health")
public class HealthController {

    private final JdbcTemplate jdbcTemplate;

    public HealthController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public Map<String, String> health() {
        try {
            // Query system info: current database name (verifies connection + schema access)
            String dbName = jdbcTemplate.queryForObject("SELECT current_database()", String.class);
            return Map.of("status", "up", "database", dbName);
        } catch (Exception e) {
            return Map.of("status", "down", "error", e.getMessage());
        }
    }
}
