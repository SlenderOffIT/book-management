package com.app.book_management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class ExecutionMetricService {

    private final JdbcTemplate jdbcTemplate;

    public void saveExecutionTime(String methodName, long executionTime) {
        String sql = "INSERT INTO metric (method_name, execution_time, execution_date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, methodName, executionTime, new Timestamp(System.currentTimeMillis()));
    }
}
