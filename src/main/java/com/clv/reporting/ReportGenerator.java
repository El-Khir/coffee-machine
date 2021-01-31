package com.clv.reporting;

import com.clv.repository.Purchase;

import java.util.List;

public interface ReportGenerator {

    Report generate(List<Purchase> purchases);
}
