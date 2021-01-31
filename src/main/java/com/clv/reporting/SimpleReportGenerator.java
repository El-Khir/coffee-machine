package com.clv.reporting;

import com.clv.order.DrinkType;
import com.clv.repository.Purchase;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class SimpleReportGenerator implements ReportGenerator {

    public Report generate(List<Purchase> purchases) {
        Map<DrinkType, Long> purchaseCount = purchases.stream()
                .collect(groupingBy(Purchase::getDrink, counting()));
        return new Report(purchaseCount);
    }




}
