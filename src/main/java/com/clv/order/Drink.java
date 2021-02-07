package com.clv.order;

public abstract class Drink {

    protected final DrinkType drinkType;

    public Drink(DrinkType drinkType) {
        if(drinkType == null) {
            throw new IllegalArgumentException("drink cannot be null");
        }
        this.drinkType = drinkType;
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public SugarQuantity getSugarQuantity() {
        return SugarQuantity.ZERO;
    }

    public boolean isExtraHot(){
        return false;
    }

}
