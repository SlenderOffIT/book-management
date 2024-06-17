package com.app.book_management.controller;

import com.app.book_management.service.stats.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/stats")
public class StatsController {

    private final StatsService statsService;

    @GetMapping("/{viewName}")
    public List<?> getView(@PathVariable String viewName) {
        return statsService.getView(viewName);
    }
}

