package com.clv.reporting;

import com.clv.order.DrinkType;

import java.util.Map;

public class Report {

    private final Map<DrinkType, Long> soldDrinksCount;

    public Report(Map<DrinkType, Long> soldDrinksCount) {
        this.soldDrinksCount = soldDrinksCount;
    }

    public Map<DrinkType, Long> getSoldDrinksCount() {
        return soldDrinksCount;
    }

    public long totalEarnedMoney() {
        return soldDrinksCount.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }
}
