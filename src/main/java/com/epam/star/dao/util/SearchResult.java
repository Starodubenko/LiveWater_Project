package com.epam.star.dao.util;

import java.util.HashMap;
import java.util.Map;

public class SearchResult {
    private Map<Integer, Integer> foundEntitiesMap = new HashMap<>();
    private int totalFoundEntitiesCount = 0;

    public Map<Integer, Integer> getFoundEntitiesMap() {
        return foundEntitiesMap;
    }

    public void setFoundEntitiesMap(Map<Integer, Integer> foundEntitiesMap) {
        this.foundEntitiesMap = foundEntitiesMap;
    }

    public int getTotalFoundEntitiesCount() {
        return totalFoundEntitiesCount;
    }

    public void setTotalFoundEntitiesCount(int totalFoundEntitiesCount) {
        if (totalFoundEntitiesCount > this.totalFoundEntitiesCount)
        this.totalFoundEntitiesCount = totalFoundEntitiesCount;
    }
}
