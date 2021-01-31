package com.clv.order;

public enum HotDrinkType implements DrinkType {

    TEA("T", 40),
    COFFEE("C", 60),
    CHOCOLATE("H", 50);

    private final String code;
    private final int price;

    HotDrinkType(String code, int price) {
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