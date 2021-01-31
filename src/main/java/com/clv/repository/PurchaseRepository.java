package com.clv.repository;

import java.util.List;

public interface PurchaseRepository {

    Purchase save(Purchase purcchase);
    List<Purchase> getAll();
}
