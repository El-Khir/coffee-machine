package com.clv.order;

public enum CoolDrinkType implements DrinkType {

    ORANGE_JUICE("O", 60);

    private final String code;
    private final int price;

    CoolDrinkType(String code, int price) {
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
