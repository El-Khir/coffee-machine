package com.clv.order;

import static com.clv.order.DrinkType.COOL_DRINKS;
import static com.clv.order.DrinkType.HOT_DRINKS;

public class DrinkFactory {

    public Drink createDrink(DrinkType drinkType, SugarQuantity sugarQuantity, boolean isExtraHot) {

        if(isHotDrink(drinkType)) {
            return new HotDrink(drinkType, sugarQuantity, isExtraHot);
        }
        if(isCoolDrink(drinkType)) {
            return new CoolDrink(drinkType);
        }
        throw new IllegalArgumentException("Unknown drink type");
    }

    public Drink createDrink(DrinkType drinkType) {
        return createDrink(drinkType, SugarQuantity.ZERO, false);
    }

    private boolean isHotDrink(DrinkType drinkType) {
        return HOT_DRINKS.contains(drinkType);
    }

    private boolean isCoolDrink(DrinkType drinkType) {
        return COOL_DRINKS.contains(drinkType);
    }
}
