package com.clv.repository;

import java.util.LinkedList;
import java.util.List;

public class InMemoryPurchaseRepository implements PurchaseRepository {

    List<Purchase> purchases = new LinkedList<>();

    @Override
    public Purchase save(Purchase purchase) {
        purchases.add(purchase);
        return purchase;
    }

    @Override
    public List<Purchase> getAll() {
        return purchases;
    }
}
