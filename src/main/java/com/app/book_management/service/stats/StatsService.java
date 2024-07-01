package com.app.book_management.service.stats;

import com.app.book_management.entity.stat.StatMadeRequest;

import java.util.List;
import java.util.Map;

public interface StatsService {

    List<?> getView(String viewName);

    List<?> prefabricatedQuery(StatMadeRequest request);
}
