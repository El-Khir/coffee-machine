package com.clv.order;

import java.util.Set;

import static com.clv.utils.CollectionUtils.createImmutableSet;

public enum DrinkType {


    TEA("T", 40),
    COFFEE("C", 60),
    CHOCOLATE("H", 50),
    ORANGE_JUICE("O", 60);

    public static final Set<DrinkType> HOT_DRINKS = createImmutableSet(TEA, COFFEE, CHOCOLATE);
    public static final java.util.Set<DrinkType> COOL_DRINKS = createImmutableSet(ORANGE_JUICE);

    private final String code;
    private final int price;

    DrinkType(String code, int price) {
        this.code = code;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public int getPrice() {
        return price;
    }

}
