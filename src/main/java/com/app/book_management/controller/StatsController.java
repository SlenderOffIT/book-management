package com.app.book_management.controller;

import com.app.book_management.entity.stat.StatMadeRequest;
import com.app.book_management.service.stats.StatsService;
import com.app.book_management.util.ExecutionTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ExecutionTime
    @PostMapping("/prefabricated")
    public List<?> prefabricatedQuery(@RequestBody StatMadeRequest request) {
        return statsService.prefabricatedQuery(request);
    }
}

