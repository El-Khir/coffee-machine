package com.clv.order;

public class Order {

    private final Drink drink;
    private final int money;

    public Order(Drink drink, int money) {
        if(drink == null) {
            throw new IllegalArgumentException("drink cannot be null");
        }
        if(money < 0) {
            throw new IllegalArgumentException("money cannot have negative value");
        }
        this.drink = drink;
        this.money = money;
    }

    public Drink getDrink() {
        return drink;
    }

    public int getMoney() {
        return money;
    }

    public DrinkType getDrinkType() { return drink.getDrinkType();}

}
