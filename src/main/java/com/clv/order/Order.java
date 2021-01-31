package com.clv.order;

public abstract class Order {

    protected final DrinkType drink;
    protected final int money;

    public Order(DrinkType drink, int money) {
        if(drink == null) {
            throw new IllegalArgumentException("drink cannot be null");
        }
        if(money < 0) {
            throw new IllegalArgumentException("money cannot have negative value");
        }
        this.drink = drink;
        this.money = money;
    }

    public DrinkType getDrink() {
        return drink;
    }

    public int getMoney() {
        return money;
    }

    public SugarQuantity getSugarQuantity() {
        return SugarQuantity.ZERO;
    }

    public boolean isExtraHot(){
        return false;
    }
}
