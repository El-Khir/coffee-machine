package com.clv.order;

public enum SugarQuantity {

    ZERO(0), ONE(1), TWO(2);

    private final int intValue;

    SugarQuantity(int intValue) {
        this.intValue = intValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public String getStringValue() {
        return String.valueOf(intValue);
    }
}
