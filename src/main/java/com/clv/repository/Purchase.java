package com.clv.repository;

import com.clv.order.DrinkType;

public class Purchase {

    DrinkType drink;
    //maybe sugar quantity too ...

    public Purchase(DrinkType drink) {
        this.drink = drink;
    }

    public DrinkType getDrink() {
        return drink;
    }
}
